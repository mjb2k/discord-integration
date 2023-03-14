![Discord Integration](assets/header.png)
A fork of a Spigot Plugin to sync your Discord server text channel with Minecraft chat

You can download the original versions from [releases](https://github.com/dominik-korsa/discord-integration/releases).
For usage instructions see the [Spigot Resources listing](https://www.spigotmc.org/resources/discord-integration.91088/).

# Credit
Discord emoji list is from [Snarr/discord-emoji-convert](https://github.com/Snarr/discord-emoji-convert)
<br></br>
Dominik Korsa is the main author of the plugin, this fork implements some personal needs and fixes I required for my
server.

# Discord server
You can get support and get information about new releases on this Discord server:
https://discord.gg/UZjrbpk6ht

# The Fork
This fork includes two integrations a fix, and some new commands:

* Adds imagemaps integration (https://www.spigotmc.org/resources/imagemaps.81851/)
![img.png](assets/image-maps.png)
  * Allows users to submit images in a discord channel and automatically upload to the servers imagemaps folder.
  * Verifies images are REAL PNG files

* Adds Plan integration
  * Adds ability to see linked discords in Plan's Player List page.

* Fixes bug that would break chat logging from discord if the chat was moved to another category.
* Adds command to list all linked accounts /di linklist
* Adds special name features to also show a players name + discord name on discord.
