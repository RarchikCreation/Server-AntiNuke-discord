# Server-AntiNuke-discord

This repository contains a Discord bot built using JDA.

## 📌 How to Create a Discord Bot
- Create a Discord Application
  - Go to the [Discord Developer Portal](https://discord.com/developers/applications).
  - Click **"New Application"** and enter a name for your bot.
  - Navigate to the **"Bot"** tab and click **"Add Bot"**.

- Configure the Bot
  - Click **"Reset Token"** and confirm to generate a new token.
  - Click **"Copy"** to save the bot token (you will need this later).
  - Enable the following **"Privileged Gateway Intents"**:
 ```
 - Presence Intent
 - Server Members Intent*
 - Message Content Intent
 - Click "Save Changes".
```

- Invite the Bot to Your Server
  - Go to the **OAuth2 → URL Generator** section.
  - Select **"bot"** and **"applications.commands"** scopes.
  - Assign necessary bot permissions (e.g., **Administrator** or specific permissions).
  - Copy the generated URL and open it in your browser to add the bot to your server.
 
## 📦 Installation

- **Clone the repository:**
   ```sh
   git clone https://github.com/RarchikCreation/Server-AntiNuke-discord.git
   cd Server-AntiNuke-discord
   ```

- **Install Java 17 (if not installed):**  
   - Download and install [Corretto 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html) for your OS.
   - Verify installation:
     ```sh
     java -version
     ```
     It should output something like:
     ```
     openjdk version "17.0.x" ...
     ```

- **Install dependencies manually (if using IntelliJ IDEA):**  
   - Open the project in IntelliJ IDEA.
   - Navigate to **File → Project Structure → Modules**.
   - Select the **Dependencies** tab and click **+ → JARs or directories**.
   - Add all `.jar` files from the `libraries/` folder.

- **Install dependencies via Gradle (alternative method):**  
   If using Gradle, add the following dependencies to `build.gradle`:

   ```gradle
   dependencies {
       implementation 'org.apache.commons:commons-collections4:4.4'
       implementation 'io.github.cdimascio:dotenv-java:2.2.0'
       implementation 'com.fasterxml.jackson.core:jackson-annotations:2.15.3'
       implementation 'com.fasterxml.jackson.core:jackson-core:2.15.3'
       implementation 'com.fasterxml.jackson.core:jackson-databind:2.15.3'
       implementation 'net.dv8tion:JDA:5.0.3'
       implementation 'org.jetbrains.kotlin:kotlin-stdlib:1.8.22'
       implementation 'com.neovisionaries:nv-websocket-client:2.14'
       implementation 'com.squareup.okhttp3:okhttp:4.11.0'
       implementation 'com.squareup.okio:okio:2.10.0'
       implementation 'org.slf4j:slf4j-api:2.0.9'
       implementation 'org.slf4j:slf4j-simple:2.0.9'
       implementation 'org.xerial:sqlite-jdbc:3.45.1.0'
       implementation 'net.sf.trove4j:trove4j:3.0.3'
   }
   ```


## 🔧 Configuration
- Create a .env file in the project source directory.
- Specify your bot token by writing it like this
```
TOKEN=your_bot_token
```

Вот дополнение с инструкцией по запуску и лицензией Apache 2.0:  

---

## 🚀 Running the Bot

### Run with Java
If you have built the `.jar` file, you can run it using:  
```sh
java -jar Server-AntiNuke-discord.jar
```

### Run with Gradle
If you are using Gradle, run the bot with:  
```sh
./gradlew run
```
(For Windows, use `gradlew.bat run` instead.)  

### Run in IntelliJ IDEA
1. Open the project in IntelliJ IDEA.  
2. Navigate to **Run → Edit Configurations**.  
3. Click **+ → Application**, set the main class to your bot's entry point.  
4. Click **Apply → OK**, then run the project.

---

## 📝 Project Description

Bot for processing user actions and sending embeds to the
desired channel. Server security is the highest priority and if you need to keep admin rights on roles, but you are worried about server security - this is what you need

- **User processing**:
- **Utilities working with the database**
- **Sending logs**:
- **Environment configuration**: uses `.env` for secure storage of confidential data.

## 🛠 Technologies Used
- Java
- JDA
- Dotenv
- sql
- 
## 📩 Support & Issues
If you find any bugs or have feature requests, feel free to open an issue in the [GitHub Issues](https://github.com/RarchikCreation/Server-AntiNuke-discord/issues).  

## 📜 License

This project is licensed under the **Apache License 2.0**.  

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

For more details, see the [LICENSE](LICENSE) file in the repository.
