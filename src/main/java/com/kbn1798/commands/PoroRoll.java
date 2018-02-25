package com.kbn1798.commands;

import java.util.Random;

import com.kbn1798.utils.BotUtils;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class PoroRoll {

	public static void processRoll(MessageReceivedEvent event) {
		String fullCmd = event.getMessage().getContent();
		String[] split = fullCmd.split(" ");
		BotUtils.sendMessage(event.getChannel(), process(split, event));
	}
	
	private static String process(String[] s, MessageReceivedEvent e) {
		if(!s[0].equalsIgnoreCase("/roll")) {
			BotUtils.sendMessage(e.getChannel(), "Oops! Improper format detected. " + s[0]+ " "+s[1]);
			return null;
		}else if(s.length>=2) {
			System.out.println("recognised cmd");
			String[] s2 = s[1].split("d"); //1d100
			int numDie;
			int dieVal;
			try {
				numDie = Integer.parseInt(s2[0]);
				dieVal = Integer.parseInt(s2[1]);
			}catch(Exception ex) {
				BotUtils.sendMessage(e.getChannel(), s2[0]+" or "+s2[1]+" cant be interpreted as a number, please reformat!");
				return null;
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
			return calcForm(numDie, dieVal, mod);
			
		}
		return "Something went terriably wrong!";
	}
	
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
