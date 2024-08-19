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
以下の通り、Controllers, Services, Repositories, Modelsを作成し、<br><br>

フロントエンド ←→ axios ←→ Controllers ←→ Services ←→ Repositories ←→ Models（ORマッパーでDBと連携）<br><br>

のような流れで処理を行っています。<br>

### Controllers
・TaskRestController.java <br>
→　REST Controller。フロントエンドからのリクエストをさばき、Servicesの処理を呼び出し、結果をレスポンスする。<br>

### Services
・TaskService.java<br>
→　ビジネスロジックを担当。Controllersからのアクセスに応じ、ビジネスロジックの元、Repositoriesの処理を呼び出し、結果をControllersに返却する。<br>

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


## 苦労した点、解決方法

＜苦労した点＞<br>
- アノテーションの理解と使いこなしが苦労した点です。<br>
SpringBootは「アテーション」を使い倒すフレームワークであり、たった1行（例：@RestController）を書くだけでその後のコードが多様な機能を持つようになります。<br>
記述自体は簡単ではありますが、その1行により、たくさんの機能を発揮するため、各アノテーションをつける意味や効果を理解するのに苦労しました。<br>

＜解決方法＞<br>
- アノテーションに関するネット記事や動画にて使用方法等を学習しながら、適宜活用しました。<br>
決まりごとのように暗記するのではなく、各アノテーションをつけることでどうなるのか（例：Modelクラスに＠Dataをつけることで、getterやsetterなどを自動生成してくれる。）というところまで学習することで、<br>
アノテーションをつける必要性や効果をより深くまで知ることができました。

## 今後やりたいこと

- 認証機能の実装
  - 本プロジェクトの認証機能はFirebase Authenticationを使用して作成しました。（フロントエンドの学習に重きを置いているため）<br>
  しかし、今後は自力で実装してバックエンドの認証周りの知識も学んでみたいなと思っています。
  <br>
- セキュリティ強化
  - 本プロジェクトにおいて、セキュリティ面に関しては実装が不十分であると感じております。<br>
  実際のプロジェクトでは、個人情報を扱うサービス等においてセキュリティは一番重要と言っても過言ではないと思うので、<br>
  今後、しっかり勉強していきたいです。


## 最後に

最後までお読みいただきありがとうございました。<br>

この個人開発を通して、これまでインプットした内容をもとに、自力で実際に動くアプリケーションを作ることができ、とても自信がつきました。<br>

開発をする過程で様々なエラーにぶつかり、それを乗り越えることで、エンジニアとしてのレベルが上がっていくのを、身をもって体験することができました。<br>

今後もアウトプットをたくさんし、技術レベルを上げていきたいと思います！！！<br>

ありがとうございました！
****
