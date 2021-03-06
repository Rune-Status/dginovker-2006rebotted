package com.rs2.net.packets.impl;

import com.rs2.game.players.Player;
import com.rs2.game.players.PlayerHandler;
import com.rs2.net.packets.PacketType;

/**
 * Challenge Player
 **/
public class ChallengePlayer implements PacketType {

	@Override
	public void processPacket(Player player, int packetType, int packetSize) {
		switch (packetType) {
		case 128:
			int answerPlayer = player.getInStream().readUnsignedWord();
		    if(PlayerHandler.players[answerPlayer] == null || answerPlayer == player.playerId)
                return;

			if (player.duelingArena() || player.duelStatus == 5) {
				player.getPacketSender().sendMessage("You can't challenge inside the arena!");
				return;
			}
			if (player.inDuelArena()) {
				player.getDueling().requestDuel(answerPlayer);
			}
			break;
		}
	}
}
