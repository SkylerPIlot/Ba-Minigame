/*
 * Copyright (c) 2017, honeyhoney <https://github.com/honeyhoney>
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
package begosrs.barbarianassault.attackstyle;

import static begosrs.barbarianassault.attackstyle.AttackStyle.CASTING;
import static begosrs.barbarianassault.attackstyle.AttackStyle.DEFENSIVE;
import static begosrs.barbarianassault.attackstyle.AttackStyle.DEFENSIVE_CASTING;
import static begosrs.barbarianassault.attackstyle.AttackStyle.OTHER;
import net.runelite.api.Client;
import net.runelite.api.EnumID;
import net.runelite.api.ParamID;
import net.runelite.api.StructComposition;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Contains logic copied from AttackStylePlugin#getWeaponTypeStyles
 */
@Singleton
public class AttackStyleUtil
{
	private final Client client;

	@Inject
	public AttackStyleUtil(Client client)
	{
		this.client = client;
	}

	public AttackStyle[] getWeaponTypeStyles(int weaponType)
	{
		// from script4525
		int weaponStyleEnum = client.getEnum(EnumID.WEAPON_STYLES).getIntValue(weaponType);
		if (weaponStyleEnum == -1)
		{
			// Blue moon spear
			if (weaponType == 22)
			{
				return new AttackStyle[]{
						AttackStyle.ACCURATE, AttackStyle.AGGRESSIVE, null, DEFENSIVE, CASTING, DEFENSIVE_CASTING
				};
			}

			if (weaponType == 30)
			{
				// Partisan
				return new AttackStyle[]{
						AttackStyle.ACCURATE, AttackStyle.AGGRESSIVE, AttackStyle.AGGRESSIVE, DEFENSIVE
				};
			}
			return new AttackStyle[0];
		}
		int[] weaponStyleStructs = client.getEnum(weaponStyleEnum).getIntVals();

		AttackStyle[] styles = new AttackStyle[weaponStyleStructs.length];
		int i = 0;
		for (int style : weaponStyleStructs)
		{
			StructComposition attackStyleStruct = client.getStructComposition(style);
			String attackStyleName = attackStyleStruct.getStringValue(ParamID.ATTACK_STYLE_NAME);

			AttackStyle attackStyle = AttackStyle.valueOf(attackStyleName.toUpperCase());
			if (attackStyle == OTHER)
			{
				// "Other" is used for no style
				++i;
				continue;
			}

			// "Defensive" is used for Defensive and also Defensive casting
			if (i == 5 && attackStyle == DEFENSIVE)
			{
				attackStyle = DEFENSIVE_CASTING;
			}

			styles[i++] = attackStyle;
		}
		return styles;
	}
}
