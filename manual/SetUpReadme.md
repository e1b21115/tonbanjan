# [セットアップマニュアル] (最終更新日 2024/1/15)
## サーバーのアクセス
### 0．IPアドレスとパスワード
IPアドレス: 150.89.233.204

パスワード: isDev23?204

※学外からのアクセスにはVPNの接続が必須となります.
### 1．アクセス方法
・isdev-bash-バージョン名.exeを起動して以下のsshコマンドでIPアドレスにアクセスします.

    $ ssh isdev23@150.89.233.204              

・パスワードを求められるので,以下のようにパスワードを入力し実行します.

    isdev23@150.89.233.204's password:isDev23?204

※パスワードの入力中,入力内容は表示されません.

### 2. webアプリケーションがあるフォルダへの移動
・以下のコマンドでホームディレクトリへ移動します.

    $ cd    
・移動後ホームディレクトリに移動できたかを確認します.以下のように表示されます.
    
    isdev23@ubuntu204:~$ pwd
    /home/isdev23
    
### 3.リポジトリのクローン
・ブラウザでGitHubにアクセスし、対象のリポジトリのURLを取得します.

・端末からリポジトリURL(https://github.com/e1b21115/tonbanjan.git)で以下のコマンドを実行し,クローンします.

    isdev23@ubuntu204:~$ git clone https://github.com/e1b21115/tonbanjan.git

・リポジトリのが正しくクローンできたかを確認するために,lsコマンドを実行します.以下のように表示されます.

    isdev23@ubuntu204:~$ ls
    tonbanjan

・確認出来たら,cdコマンドを使用しリポジトリに移動します.

    isdev23@ubuntu204:~$ cd tonbanjan

・※すでにサーバ上にリポジトリをクローンしている場合

・アプリケーションが存在するディレクトリ(/home/isdev23/tonbanjan)に移動し,以下のコマンドを実行します.

    $ git pull origin main

### 4.Gradleを利用したwebアプリケーションの実行
・webアプリケーションを実行は以下のコマンドで実行してください.

    isdev23@ubuntu204:~$ bash ./gradlew

・以下が実行結果です.

    Starting a Gradle Daemon (subsequent builds will be faster)
    
    > Task :help
    
    Welcome to Gradle 8.3.
    
    To run a build, run gradlew <task> ...
    
    To see a list of available tasks, run gradlew tasks
    
    To see more detail about a task, run gradlew help --task <task>
    
    To see a list of command-line options, run gradlew --help
    
    For more detail on using Gradle, see https://docs.gradle.org/8.3/userguide/command_line_interface.html
    
    For troubleshooting, visit https://help.gradle.org
    
    BUILD SUCCESSFUL in 4s
    1 actionable task: 1 executed

・プロジェクトの実行のために以下のコマンドを入力し実行してください.

    isdev23@ubuntu204:~$ bash ./gradlew bootrun

・以下が実行結果です.

    > Task :bootRun
    
      .   ____          _            __ _ _
     /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
     \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
      '  |____| .__|_| |_|_| |_\__, | / / / /
     =========|_|==============|___/=/_/_/_/
     :: Spring Boot ::                (v3.1.5)
    
    2024-01-14T19:05:53.286+09:00  INFO 9474 --- [           main] 
    …
    2024-01-14T22:09:24.889+09:00  INFO 10425 --- [           main] ipeko.tonbanjan.TonbanjanApplication     : Started TonbanjanApplication in 1.226 seconds (process running for 1.371)
    <==========---> 80% EXECUTING [5s]
    > :bootRun

### 5.webアプリケーションへのアクセス

・実行後、「 http://150.89.233.204 」をブラウザでアクセスすると、webアプリケーションを利用できます。
