/*
 * Copyright (c) 2019, 7ate9 <https://github.com/se7enAte9>
 * Copyright (c) 2020, BegOsrs <https://github.com/BegOsrs>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package begosrs.barbarianassault.menuentryswapper;

import begosrs.barbarianassault.BaMinigameConfig;
import begosrs.barbarianassault.BaMinigamePlugin;
import begosrs.barbarianassault.Role;
import begosrs.barbarianassault.Wave;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.MenuAction;
import net.runelite.api.MenuEntry;
import net.runelite.client.util.Text;

@Slf4j
public class MenuEntrySwapper
{

	private final BaMinigameConfig config;
	private final Client client;
	private final BaMinigamePlugin plugin;

	private final Multimap<String, Swap> swaps;
	private final Set<Hide> hides;
	private final ArrayListMultimap<String, Integer> optionIndexes;

	@Inject
	MenuEntrySwapper(final BaMinigameConfig config, final Client client, BaMinigamePlugin plugin)
	{
		this.config = config;
		this.client = client;
		this.plugin = plugin;
		this.swaps = LinkedHashMultimap.create();
		this.hides = new HashSet<>();
		this.optionIndexes = ArrayListMultimap.create();
	}

	private static <T extends Comparable<? super T>> void sortedInsert(List<T> list, T value)
	{
		int idx = Collections.binarySearch(list, value);
		list.add(idx < 0 ? -idx - 1 : idx, value);
	}

	public void enableSwaps()
	{
		addSwap("talk-to", "get-rewards", config::swapGetRewards);

		addSwap("climb-down", "quick-start", config::swapQuickStart);

		addSwap("look-in", "empty", config::swapCollectionBag);
		addSwap("use", "tell-defensive", "collector horn", config::swapCollectorHorn);
		addSwap("use", "destroy", "red egg", config::swapDestroyEggs);
		addSwap("use", "destroy", "green egg", config::swapDestroyEggs);
		addSwap("use", "destroy", "blue egg", config::swapDestroyEggs);

		addSwap("drink-from", "take-from", config::swapHealerSpring);

		addHide("attack", "penance", () -> shouldHideAttOptions(Role.COLLECTOR));
		addHide("attack", "queen spawn", () -> shouldHideAttOptions(Role.COLLECTOR));
		addHide("attack", "penance", () -> shouldHideAttOptions(Role.DEFENDER));
		addHide("attack", "queen spawn", () -> shouldHideAttOptions(Role.DEFENDER));
		addHide("attack", "penance", () -> shouldHideAttOptions(Role.HEALER));
		addHide("attack", "queen spawn", () -> shouldHideAttOptions(Role.HEALER));
	}

	/**
	 * Determines whether to hide attack options for a specific role, based on config and in-game
	 */
	private boolean shouldHideAttOptions(Role role)
	{
		if (plugin.getInGameBit() != 1)
		{
			return false;
		}
		Wave wave = plugin.getWave();
		Role currentRole = null;
		if (wave != null)
		{
			currentRole = wave.getRole();
		}
		if (currentRole == null || !Objects.equals(currentRole, role))
		{
			return false;
		}
		switch (role)
		{
			case COLLECTOR:
				return config.hideAttackOptionsCollector();
			case DEFENDER:
				return config.hideAttackOptionsDefender();
			case HEALER:
				return config.hideAttackOptionsHealer();
		}
		return false;
	}

	public void disableSwaps()
	{
		swaps.clear();
	}

	public void performSwaps()
	{
		// The menu is not rebuilt when it is open, so don't swap or else it will
		// repeatedly swap entries
		if (client.getGameState() != GameState.LOGGED_IN || client.isMenuOpen())
		{
			return;
		}

		MenuEntry[] menuEntries = client.getMenuEntries();

		// Build option map for quick lookup in findIndex
		int idx = 0;
		optionIndexes.clear();
		for (MenuEntry entry : menuEntries)
		{
			String option = Text.removeTags(entry.getOption()).toLowerCase();
			optionIndexes.put(option, idx++);
		}

		// Perform swaps
		idx = 0;
		for (MenuEntry entry : menuEntries)
		{
			swapMenuEntry(idx++, entry);
		}

		// Perform hiding
		MenuEntry[] filteredEntries = filterHidden(client.getMenuEntries());
		if (filteredEntries.length != menuEntries.length)
		{
			client.setMenuEntries(filteredEntries);
		}
	}

	/**
	 * Filters out menu entries that should be hidden.
	 *
	 * @return the filtered array of entries
	 */
	private MenuEntry[] filterHidden(MenuEntry[] menuEntries)
	{
		List<MenuEntry> filtered = new ArrayList<>();
		for (MenuEntry entry : menuEntries)
		{
			String option = Text.standardize(entry.getOption());
			String target = Text.standardize(entry.getTarget());
			boolean shouldAdd = hides.stream().noneMatch(h ->
				h.getEnabled().get() &&
					h.getOptionPredicate().test(option) &&
					h.getTargetPredicate().test(target));
			if (shouldAdd)
			{
				filtered.add(entry);
			}
		}
		return filtered.toArray(new MenuEntry[0]);
	}

	private void swapMenuEntry(int index, MenuEntry menuEntry)
	{
		final String option = Text.removeTags(menuEntry.getOption()).toLowerCase();
		final String target = Text.removeTags(menuEntry.getTarget()).toLowerCase();

		Collection<Swap> swaps = this.swaps.get(option);

		for (Swap swap : swaps)
		{
			if (swap.getTargetPredicate().test(target) && swap.getEnabled().get())
			{
				if (performSwap(swap.getSwappedOption(), target, index, swap.isStrict()))
				{
					break;
				}
			}
		}
	}

	private void addSwap(String option, String swappedOption, Supplier<Boolean> enabled)
	{
		addSwap(option, s -> true, swappedOption, enabled);
	}

	private void addSwap(String option, String swappedOption, String target, Supplier<Boolean> enabled)
	{
		addSwap(option, s -> Objects.equals(s, target), swappedOption, enabled);
	}

	private void addSwap(String option, Predicate<String> targetPredicate, String swappedOption, Supplier<Boolean> enabled)
	{
		swaps.put(option, new Swap(s -> true, targetPredicate, swappedOption, enabled, true));
	}

	private void addHide(String option, String targetContains, Supplier<Boolean> enabled)
	{
		hides.add(new Hide(s -> Objects.equals(s, option), s -> s.contains(targetContains), enabled, true));
	}

	private boolean performSwap(String option, String target, int index, boolean strict)
	{
		MenuEntry[] menuEntries = client.getMenuEntries();

		// find option to swap with
		int optionIdx = findIndex(menuEntries, index, option, target, strict);

		if (optionIdx >= 0)
		{
			performSwap(optionIndexes, menuEntries, optionIdx, index);
			return true;
		}

		return false;
	}

	private void performSwap(ArrayListMultimap<String, Integer> optionIndexes, MenuEntry[] entries, int index1, int index2)
	{
		MenuEntry entry1 = entries[index1],
			entry2 = entries[index2];

		entries[index1] = entry2;
		entries[index2] = entry1;

		if (entry1.isItemOp() && entry1.getType() == MenuAction.CC_OP_LOW_PRIORITY)
		{
			entry1.setType(MenuAction.CC_OP);
		}
		if (entry2.isItemOp() && entry2.getType() == MenuAction.CC_OP_LOW_PRIORITY)
		{
			entry2.setType(MenuAction.CC_OP);
		}

		client.setMenuEntries(entries);

		// Update optionIndexes
		String option1 = Text.removeTags(entry1.getOption()).toLowerCase(),
			option2 = Text.removeTags(entry2.getOption()).toLowerCase();

		List<Integer> list1 = optionIndexes.get(option1),
			list2 = optionIndexes.get(option2);

		// call remove(Object) instead of remove(int)
		list1.remove((Integer) index1);
		list2.remove((Integer) index2);

		sortedInsert(list1, index2);
		sortedInsert(list2, index1);
	}

	private int findIndex(MenuEntry[] entries, int limit, String option, String target, boolean strict)
	{
		if (strict)
		{
			List<Integer> indexes = optionIndexes.get(option);

			// We want the last index which matches the target, as that is what is top-most
			// on the menu
			for (int i = indexes.size() - 1; i >= 0; --i)
			{
				int idx = indexes.get(i);
				MenuEntry entry = entries[idx];
				String entryTarget = Text.removeTags(entry.getTarget()).toLowerCase();

				// Limit to the last index which is prior to the current entry
				if (idx < limit && (target == null || entryTarget.equals(target)))
				{
					return idx;
				}
			}
		}
		else
		{
			// Without strict matching we have to iterate all entries up to the current limit...
			for (int i = limit - 1; i >= 0; i--)
			{
				MenuEntry entry = entries[i];
				String entryOption = Text.removeTags(entry.getOption()).toLowerCase();
				String entryTarget = Text.removeTags(entry.getTarget()).toLowerCase();

				if (entryOption.contains(option.toLowerCase()) && entryTarget.equals(target))
				{
					return i;
				}
			}

		}

		return -1;
	}
}
