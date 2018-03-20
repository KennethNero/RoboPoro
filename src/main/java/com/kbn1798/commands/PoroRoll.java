package com.kbn1798.commands;

import java.util.Random;

import com.kbn1798.utils.BotUtils;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class PoroRoll {
	
	/***
	 * Base method for roll processing.
	 * @param event The event that triggered this method to be fired.
	 */
	public static void processRoll(MessageReceivedEvent event) {
		String fullCmd = event.getMessage().getContent();
		String[] split = fullCmd.split(" ");
		process(split, event);
	}
	
	/***
	 * Works through processing 'rolls' and potential joke commands, serves as the main parser
	 * of this specific command group.
	 * @param s A string array consisting of each argument as separated by spaces.
	 * @param e The event that triggered the containing method to be fired.
	 */
	private static void process(String[] s, MessageReceivedEvent e) {
		if(!s[0].equalsIgnoreCase("/roll")) { //General catch all for badly done commands.
			BotUtils.sendMessage(e.getChannel(), "Oops! Improper format detected. " + s[0]+ " "+s[1]);
			return;
		}else if(s.length>=2) { // Container for meme and base roll commands.
			if(s[1].equalsIgnoreCase("froshboard")) {
				BotUtils.sendMessage(e.getChannel(), "Conditional!");
				return;
			}
			String[] s2 = s[1].split("d"); //Split commands like '1d100' to 1, and 100.
			int numDie;
			int dieVal;
			try {
				numDie = Integer.parseInt(s2[0]);
				dieVal = Integer.parseInt(s2[1]);
			}catch(Exception ex) {
				BotUtils.sendMessage(e.getChannel(), s[1]+" cant be interpreted as a roll argument, please reformat!");
				return;
			}
			//Deals with processing s[2] or the modifier
			int mod = 0;
			if(s.length==3) {//has a modifier
				char ope='+';
				try {
					ope = s[2].charAt(0);
				}catch(Exception exc) {}
				try {
					mod = Integer.parseInt(ope+s[2].substring(1));
				}catch(Exception exception) {
					BotUtils.sendMessage(e.getChannel(), "Modifier could not be parsed as an integer, please reformat!");
				}
			}
			BotUtils.sendMessage(e.getChannel(), calcForm(numDie, dieVal, mod));
			return;
		}
		return;
	}
	
	/***
	 * Iterates through dice and final number calculation.
	 * @param die The amount of dice to be rolled
	 * @param vals The values of each dice.
	 * @param mod The modifier attached to the end of the rolls.
	 * @return A formatted string representing the outcome.
	 */
	private static String calcForm(int die, int vals, int mod) {
		StringBuilder sb = new StringBuilder();
		if(mod<0) {
			sb.append("Results of rolling "+die+"d"+vals+" "+mod+" \n\n( ");
		}else
			sb.append("Results of rolling "+die+"d"+vals+" +"+mod+" \n\n( ");
		Random rn = new Random();
		int total = mod;
		for(int i = 0; i<die;i++) {
			int r = rn.nextInt(vals)+1;
			total+=r;
			sb.append(r+", ");
		}
		sb.delete(sb.length()-2, sb.length()-1);
		if(mod <0) {
			sb.append(") "+mod+"\nTotal: "+total);	
		}else if (mod>0) {
			sb.append(")+"+mod+"\nTotal: "+total);	
		}else {
			sb.append(")\nTotal: "+total);
		}
		
		return sb.toString();
	}

}
