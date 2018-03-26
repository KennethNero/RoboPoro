# RoboPoro

RoboPoro is a discord bot utility which allows individuals to add, remove, and display pictures (poros, a cute little fluff creature from League of Legends, by default) on command. The program also includes a dice rolling mechanism if not only to add another feature to the bot.


### Basic Commands

RoboPoro comes with two sets of basic commands, /poro, and /roll.

/poro comes with 4 sub commands. "/poro list" will display the current map of pictures currently loaded into the program, and provide links in an easy access discord embedded message. /poro as a default will pick at random from the current configured map and display its picture to the user. /poro [name] will chose a specific picture by its key, and display it to the user. /poro add [name] [url] create an entry in the config for  given name url pairing, and immediately save the config. /poro remove [name] will immediately remove the given map pair associated with the name entered, and update the config.

The roll command is much more simple. The format for utilizing this feature is /roll xdy +z, where z is the number of dice to roll, y is the sidedness of die used, and z is an optional modifier to include. The result will be the randomized quality of x y-sided dice, plus the quantity of z at the end.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management