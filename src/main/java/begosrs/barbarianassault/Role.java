/*
 * Copyright (c) 2018, Cameron <https://github.com/noremac201>
 * Copyright (c) 2020, BegOsrs <https://github.com/begosrs>
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
package begosrs.barbarianassault;

import begosrs.barbarianassault.api.BaModelID;
import begosrs.barbarianassault.api.BaSpriteID;
import begosrs.barbarianassault.api.BaVarbits;
import begosrs.barbarianassault.api.widgets.BaWidgetInfo;
import com.google.common.collect.ImmutableMap;
import java.awt.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.api.SpriteID;
import net.runelite.api.widgets.Widget;

@Getter
@AllArgsConstructor
public enum Role
{
	ATTACKER("Attacker", BaMinigamePlugin.RED,
		BaWidgetInfo.BA_ATTACKER_WAVE_INFO,
		BaWidgetInfo.BA_ATTACKER_WAVE_TEXT, BaWidgetInfo.BA_ATTACKER_WAVE_SPRITE,
		BaWidgetInfo.BA_ATTACKER_LISTEN_TOP_TEXT, BaWidgetInfo.BA_HORN_OF_GLORY_COLLECTOR_LISTEN_TEXT,
		BaWidgetInfo.BA_ATTACKER_CALL_TEXT, BaWidgetInfo.BA_ATTACKER_CALL_FLASH,
		BaWidgetInfo.BA_HORN_OF_GLORY_COLLECTOR_LISTEN_TEXT, BaWidgetInfo.BA_ATTACKER_ROLE_TEXT,
		BaWidgetInfo.BA_ATTACKER_ROLE_SPRITE, BaSpriteID.BA_COLLECTOR_HORN_ICON, "Collector",
		BaVarbits.BA_ATTACKER_ROLE_BASE_POINTS, BaVarbits.BA_ATTACKER_ROLE_MULTIPLIER,
		SpriteID.BARBARIAN_ASSAULT_HORN_FOR_ATTACKER_ICON,
		BaModelID.BA_ATTACKER_ICON),
	DEFENDER("Defender", BaMinigamePlugin.LIGHT_BLUE,
		BaWidgetInfo.BA_DEFENDER_WAVE_INFO,
		BaWidgetInfo.BA_DEFENDER_WAVE_TEXT, BaWidgetInfo.BA_DEFENDER_WAVE_SPRITE,
		BaWidgetInfo.BA_DEFENDER_LISTEN_TEXT, BaWidgetInfo.BA_HORN_OF_GLORY_HEALER_LISTEN_TEXT,
		BaWidgetInfo.BA_DEFENDER_CALL_TEXT, BaWidgetInfo.BA_DEFENDER_CALL_FLASH,
		BaWidgetInfo.BA_HORN_OF_GLORY_HEALER_LISTEN_TEXT, BaWidgetInfo.BA_DEFENDER_ROLE_TEXT,
		BaWidgetInfo.BA_DEFENDER_ROLE_SPRITE, BaSpriteID.BA_HEALER_HORN_ICON, "Healer",
		BaVarbits.BA_DEFENDER_ROLE_BASE_POINTS, BaVarbits.BA_DEFENDER_ROLE_MULTIPLIER,
		SpriteID.BARBARIAN_ASSAULT_HORN_FOR_DEFENDER_ICON,
		BaModelID.BA_DEFENDER_ICON),
	COLLECTOR("Collector", Color.YELLOW,
		BaWidgetInfo.BA_COLLECTOR_WAVE_INFO,
		BaWidgetInfo.BA_COLLECTOR_WAVE_TEXT, BaWidgetInfo.BA_COLLECTOR_WAVE_SPRITE,
		BaWidgetInfo.BA_COLLECTOR_LISTEN_TEXT, BaWidgetInfo.BA_HORN_OF_GLORY_ATTACKER_LISTEN_TEXT,
		BaWidgetInfo.BA_COLLECTOR_CALL_TEXT, BaWidgetInfo.BA_COLLECTOR_CALL_FLASH,
		BaWidgetInfo.BA_HORN_OF_GLORY_ATTACKER_LISTEN_TEXT, BaWidgetInfo.BA_COLLECTOR_ROLE_TEXT,
		BaWidgetInfo.BA_COLLECTOR_ROLE_SPRITE, BaSpriteID.BA_ATTACKER_HORN_ICON, "Attacker",
		BaVarbits.BA_COLLECTOR_ROLE_BASE_POINTS, BaVarbits.BA_COLLECTOR_ROLE_MULTIPLIER,
		SpriteID.BARBARIAN_ASSAULT_HORN_FOR_COLLECTOR_ICON,
		BaModelID.BA_COLLECTOR_ICON),
	HEALER("Healer", BaMinigamePlugin.DARK_GREEN,
		BaWidgetInfo.BA_HEALER_WAVE_INFO,
		BaWidgetInfo.BA_HEALER_WAVE_TEXT, BaWidgetInfo.BA_HEALER_WAVE_SPRITE,
		BaWidgetInfo.BA_HEALER_LISTEN_TEXT, BaWidgetInfo.BA_HORN_OF_GLORY_DEFENDER_LISTEN_TEXT,
		BaWidgetInfo.BA_HEALER_CALL_TEXT, BaWidgetInfo.BA_HEALER_CALL_FLASH,
		BaWidgetInfo.BA_HORN_OF_GLORY_DEFENDER_LISTEN_TEXT, BaWidgetInfo.BA_HEALER_ROLE_TEXT,
		BaWidgetInfo.BA_HEALER_ROLE_SPRITE, BaSpriteID.BA_DEFENDER_HORN_ICON, "Defender",
		BaVarbits.BA_HEALER_ROLE_POINTS, BaVarbits.BA_HEALER_ROLE_MULTIPLIER,
		SpriteID.BARBARIAN_ASSAULT_HORN_FOR_HEALER_ICON,
		BaModelID.BA_HEALER_ICON);

	private static final ImmutableMap<String, String> GLORY_CALLS = ImmutableMap.<String, String>builder()
		.put("Controlled/Bullet/Wind", "Controlled/Bullet/Wind")
		.put("Accurate/Field/Water", "Accurate/Field/Water")
		.put("Aggressive/Blunt/Earth", "Aggressive/Blunt/Earth")
		.put("Defensive/Barbed/Fire", "Defensive/Barbed/Fire")
		.put("Tofu", "Tofu")
		.put("Crackers", "Crackers")
		.put("Worms", "Worms")
		.put("Poison worms", "Pois. Worms")
		.put("Poison tofu", "Pois. Tofu")
		.put("Poison meat", "Pois. Meat")
		.put("Red egg", "Red egg")
		.put("Green egg", "Green egg")
		.put("Blue egg", "Blue egg")
		.build();
	private static final ImmutableMap<String, Integer> ITEMS = ImmutableMap.<String, Integer>builder()
		.put("Tofu", ItemID.TOFU)
		.put("Crackers", ItemID.CRACKERS)
		.put("Worms", ItemID.WORMS)
		.put("Poison Worms", ItemID.POISONED_WORMS)
		.put("Pois. Worms", ItemID.POISONED_WORMS)
		.put("Poison Tofu", ItemID.POISONED_TOFU)
		.put("Pois. Tofu", ItemID.POISONED_TOFU)
		.put("Poison Meat", ItemID.POISONED_MEAT)
		.put("Pois. Meat", ItemID.POISONED_MEAT)
		.put("Controlled/Bullet/Wind", ItemID.BULLET_ARROW)
		.put("Accurate/Field/Water", ItemID.FIELD_ARROW)
		.put("Aggressive/Blunt/Earth", ItemID.BLUNT_ARROW)
		.put("Defensive/Barbed/Fire", ItemID.BARBED_ARROW)
		.put("Red eggs", ItemID.RED_EGG)
		.put("Green eggs", ItemID.GREEN_EGG)
		.put("Blue eggs", ItemID.BLUE_EGG)
		.build();
	private final String name;
	private final Color color;
	private final BaWidgetInfo waveInfo;
	private final BaWidgetInfo waveText;
	private final BaWidgetInfo waveSprite;
	private final BaWidgetInfo listen;
	private final BaWidgetInfo hornOfGloryListen;
	private final BaWidgetInfo call;
	private final BaWidgetInfo callFlash;
	private final BaWidgetInfo hornOfGloryCall;
	private final BaWidgetInfo roleText;
	private final BaWidgetInfo roleSprite;
	private final int partnerHornSpriteId;
	private final String partnerRoleName;
	private final BaVarbits basePointsVarBit;
	private final BaVarbits pointsMultiplierVarBit;
	private final int hornSpriteId;
	private final int roleModelId;

	@Override
	public String toString()
	{
		return name();
	}

	int getListenItemId(Client client)
	{
		final String listen = getListenText(client);
		return ITEMS.getOrDefault(listen, -1);
	}

	String getListenText(Client client)
	{
		String listen = getWidgetText(client, hornOfGloryListen, this.listen);
		if (this == ATTACKER)
		{
			if ("Controlled/".equals(listen))
			{
				listen = "Controlled/Bullet/Wind";
			}
			else if ("Accurate/".equals(listen))
			{
				listen = "Accurate/Field/Water";
			}
			else if ("Aggressive/".equals(listen))
			{
				listen = "Aggressive/Blunt/Earth";
			}
			else if ("Defensive/".equals(listen))
			{
				listen = "Defensive/Barbed/Fire";
			}
		}
		return listen;
	}

	private String getWidgetText(Client client, BaWidgetInfo hornOfGlory, BaWidgetInfo baWidgetInfo)
	{
		Widget widget = client.getWidget(hornOfGlory.getGroupId(), hornOfGlory.getChildId());
		if (widget != null)
		{
			return GLORY_CALLS.get(widget.getText());
		}

		widget = client.getWidget(baWidgetInfo.getGroupId(), baWidgetInfo.getChildId());
		if (widget != null)
		{
			return widget.getText();
		}

		return null;
	}

	public int getPoints(Client client)
	{
		final int constant = 512;
		int multiplier = client.getVarbitValue(pointsMultiplierVarBit.getId());
		return client.getVarbitValue(basePointsVarBit.getId()) + constant * multiplier;
	}
}
