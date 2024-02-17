# DiscordPostBot
---

**DiscordPostBot** is a **selfhosted** solution that makes it easy to automatically send messages to the Discord channel on behalf of the user via the Discord User API **(NOT BOT API)** by working with the user's token. This allows you to easily and quickly automate message sending on other people's servers on which you are not an administrator.
 
## Cases of use

- Are you a Discord server administrator, and do you want to notify users about important events on your behalf, and not on behalf of bots? **DiscordPostBot helps you!**
- Are you have a clan in the game and do you want to announce the recruitment? **DiscordPostBot helps you!**
- Are you a dirty spammer, and you want to ruin someone's life again? **DiscordPostBot helps you!** (please, don't use my solution for these purposes)

## Features

- Scheduled message sending.
- Sending a message to multiple channels.
- Sending a message via user token.
- Setting a random message sending time.
- Attaching any files to a message.

## Configuration
```properties
discord.token=Enter your token here

# - Discord channels id's.
# If you want to specify multiple channels to send a message, you need to write them separated by commas.
# Refer to github (https://github.com/daedal-dev-ru/DiscordPostBot to receive instructions on how to obtain a token.
# Example: 0000000000000000000,1111111111111111111
discord.channels.id=0000000000000000000,1111111111111111111

# Frequency of message sending (in seconds).
# Can't be less than or equal to 0.
# Example: 3600
discord.message.sending.period=30

# Send first message on program start instantly? true - yes, false - no
# If true, the program will send the first message as soon as it starts, then it will start sending the message
# after a delay specified in the discord.message.sending.period
# If false, the program will send the first message only after the delay specified in discord.message.sending.period
discord.message.send.on.start=true

# - Message sending random delay (in seconds).
# If you don't want to randomize sending period, you'll need set this value to 0.
# This feature adds random delay to period.
# Example: if period is 3600 and randomization range is 30, frequency of message sending will be from 3600 to 3630 seconds.
discord.message.sending.period.random.delay=0

# - Message text.
# If you want to send a multiline message, you need to separate the lines using "\n"
discord.message.content=Hello, World!\nCreated by humans

# - Paths to message attachments.
# If you want to attach multiple files, you need write them separated by commas.
# Examples: attachments/example.jpg,attachments/example2.png
discord.message.attachments=attachments/example.jpg,attachments/example2.png
```

## Setting up

### Receiving a discord token
1. Go to the [**Discord website**](https://discord.com/) using your desktop browser and log into your account.
2. Click on the three dots located in the upper right corner
3. From the dropdown menu, choose More tools, and then select **Developer tools.**
4. In the Developer Tools panel, click on the arrows to navigate to the Application tab.
5. Within the **Application** tab, find the **Storage** section, and click the down arrow next to Local storage.
6. Locate the Discord URL and click on it.
7. In the **Filter** field, type **token**
8. Click the **Toggle device toolbar** button. (Icon with PC and Phone)
9. Your Discord token will be displayed. Copy it to DiscordPostBot config.

### Install on Windows
1. Download and install Java for Windows from [official site.](https://www.java.com/en/download/)
2. Download [**the latest program release**](https://github.com/daedal-dev-ru/DiscordPostBot/releases/tag/1.0-RELEASE) from **Releases** section.
3. Optional: create bot working directory.
4. Place the .jar file in a folder convenient for you.
5. Right-click in the empty space inside folder window and select **"Open command window here"**
6. Execute command
> java -jar DiscordPostBot.jar
7. After this the program will give an error and create a configuration file **application.properties** and the **"attachments"** folder.
8. Edit the **application.properties** and place your attachments in the **"attachments"** folder.
9. Execute .jar file again using command below
> java -jar DiscordPostBot.jar
10. Done! You are beautiful!

### Install on Linux
### Debian/Ubuntu
1. Optional: create a working directory for bot and enter it
> $ sudo mkdir -p /home/DiscordPostBot && cd /home/DiscordPostBot
2. Install wget
```
$ sudo apt update
$ sudo apt install wget
```
3. Download **the latest** bot .jar file via **wget**
> $ sudo wget https://github.com/daedal-dev-ru/DiscordPostBot/releases/download/1.0-RELEASE/DiscordPostBot.jar
4. Install _Java_
```
$ sudo apt update
$ sudo apt install default-jdk
$ java -version
```
5. Execute .jar file using command below
> $ java -jar DiscordPostBot.jar
6. After this the program will give an error and create a configuration file **application.properties** and the **"attachments"** folder.
7. Edit the **application.properties** using your favorite text redactor (nano, gedit, vim or other) and place your attachments in the **"attachments"** folder.
8. Execute .jar file again using command below 
> $ java -jar DiscordPostBot.jar
9. Done!

### Fedora
1. Optional: create a working directory for bot and enter it
> $ sudo mkdir -p /home/DiscordPostBot && cd /home/DiscordPostBot
2. Install wget
> $ sudo dnf install wget
3. Download **the latest** bot .jar file via **wget**
> $ sudo wget https://github.com/daedal-dev-ru/DiscordPostBot/releases/download/1.0-RELEASE/DiscordPostBot.jar
4. Install _Java_
> $ sudo dnf install java-1.8.0-openjdk.x86_64
5. Execute .jar file using command below
> $ java -jar DiscordPostBot.jar
6. After this the program will give an error and create a configuration file **application.properties** and the **"attachments"** folder.
7. Edit the **application.properties** using your favorite text redactor (nano, gedit, vim or other) and place your attachments in the **"attachments"** folder.
8. Execute .jar file again using command below
> $ java -jar DiscordPostBot.jar
9. Done!

### Arch
1. Optional: create a working directory for bot and enter it
> $ sudo mkdir -p /home/DiscordPostBot && cd /home/DiscordPostBot
2. Install wget
> $ sudo pacman -S wget
3. Download **the latest** bot .jar file via **wget**
> $ sudo wget https://github.com/daedal-dev-ru/DiscordPostBot/releases/download/1.0-RELEASE/DiscordPostBot.jar
4. Install _Java_
> $ sudo pacman -S jre-openjdk
5. Execute .jar file using command below
> $ java -jar DiscordPostBot.jar
6. After this the program will give an error and create a configuration file **application.properties** and the **"attachments"** folder.
7. Edit the **application.properties** using your favorite text redactor (nano, gedit, vim or other) and place your attachments in the **"attachments"** folder.
8. Execute .jar file again using command below
> $ java -jar DiscordPostBot.jar
9. Done!
