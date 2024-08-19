## 概要
サービス名：<br>
Task Manager（バックエンド）

ゲスト用 ID（体験版）:<br>
メール　 → 　cnann.0615.test@gmail.com<br>
パスワード　 → 　 testes0615

URL（上記ゲスト用IDでぜひ使ってみてください！）：
https://task-manager-frontend-14.vercel.app/

フロントエンド リポジトリ：
https://github.com/cnann0615/TaskManager-frontend-14

↓ サービスイメージ ↓
![image.png](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/3817219/93309d5d-4e3b-60a4-8f97-f06f4698d351.png)

## きっかけ
2024 年に入ってから、フロントエンドについて本格的に学び始めた中で、
「Udemy 等でのインプット学習で学んだ知識を、実践レベルで身につけたい！」
と思い、開発に取り掛かりました。<br>
フロントエンドの学習が主な目的ですが、Webアプリを作るには当然バックエンドの実装も必要となるため、
以前学習したことのあるSpringBootの知識を思い出しながらバックエンド（APIサーバ）も自力で実装してみました！<br>

目的が技術定着であったため、ポートフォリオとしてありきたりな「Todoアプリ」を選びましたが、今後はオリジナリティのあるアプリケーションも作成予定です！！

## 開発環境と使用技術

### 開発環境

OS：macOS<br>
IDE：IntelliJ IDEA CE

### 使用技術

＜バックエンド＞<br>
言語：Java<br>
フレームワーク：Spring Boot<br>
データベース：MySQL（Heroku JawsDB）<br>
デプロイ：Heroku<br>
パッケージ管理ツール：Maven<br>

＜その他＞<br>
GitHub（ソースコード管理）<br>

などなど。。。

## 機能概要

APIサーバとしてデータをフロントエンドとやり取りし、DBに反映させることが主な役割です。<br>
以下の通り、Controllers, Repositories, Modelsを作成し、<br><br>

フロントエンド ←→ axios ←→ Controllers ←→ Repositories ←→ Models（ORマッパーでDBと連携）<br><br>

のような流れで処理を行っています。<br>

### Controllers
・TaskRestController.java <br>
→　REST Controller。フロントエンドからのリクエストをさばき、Repositoryの処理を呼び出し、結果をレスポンスする。<br>

### Repositories
以下それぞれのRepositoryにて、ModelおよびDBへのCRUD操作を行う。（SQL文の実行など）<br>
・TaskItemRepository.java<br>
・TaskCategoryRepository.java<br>
・TaskScheduleRepository.java<br>

### Models
以下３つのModelを作成し、ORマッパーにてDB（MySQL）と連携。<br>
・タスクアイテム（タスクそのもの）→ TaskItem.java<br>
・カテゴリ（タスクに紐づくカテゴリ）→ TaskCategory.java<br>
・スケジュール（タスクに紐づくスケジュール）→ TaskSchedule.java<br>


## 苦労した点、今後やりたいこと

＜苦労した点＞<br>
- デプロイ
	- フロントエンドのデプロイは、Vercel で行い、割と難なくできたのですが、バックエンドに大変苦戦しました。<br>
	最初は AWS を使って試みましたが、インフラ構築等の知識が乏しいが故にすぐに挫折し、Heroku で再挑戦しました。<br>
	jarファイルやapplication.properties 等の設定ファイルに関する知識が浅かったのが苦戦した原因かと思います。<br>
	- フロントエンドで使用したVerecl にしろ、今回使用したHeroku にしろ、GitHub の push と同時にデプロイされるのは、とても便利だと感じました。

＜今後やりたいこと＞<br>
- Serviceクラスの作成
  - 現在は、Controllerに多くのビジネスロジックを書いてしまっています。<br>
  本来、ビジネスロジックはServiceクラスに記載し、Controllerから呼び出すというのが基本動作となると思いますので、今後対応していきたいです。

- 認証機能の実装
  - 本プロジェクトの認証機能はFirebase Authenticationを使用して作成しました。（フロントエンドの学習に重きを置いているため）<br>
  しかし、今後は自力で実装してバックエンドの認証周りの知識も学んでみたいなと思っています。
  <br>


## 最後に

最後までお読みいただきありがとうございました。<br>

この個人開発を通して、これまでインプットした内容をもとに、自力で実施に動くアプリケーションを作ることができ、とても自信がつきました。<br>

開発をする過程で様々なエラーにぶつかり、それを乗り越えることで、エンジニアとしてのレベルが上がっていくのを、身をもって体験することができました。<br>

今後もアウトプットをたくさんし、技術レベルを上げていきたいと思います！！！<br>

ありがとうございました！
****
