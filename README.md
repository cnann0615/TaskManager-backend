## 概要
サービス名：<br>
Task Manager

ゲスト用 ID（体験版）:<br>
メール　 → 　cnann.0615.test@gmail.com<br>
パスワード　 → 　 testes0615

URL：
https://task-manager-frontend-14.vercel.app/

フロントエンド リポジトリ：
https://github.com/cnann0615/TaskManager-frontend-14

↓ サービスイメージ ↓
![image.png](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/3817219/93309d5d-4e3b-60a4-8f97-f06f4698d351.png)

## きっかけ

2024 年に入ってから、フロントエンドについて本格的に学び始めた中で、
「Udemy 等でのインプット学習で学んだ知識を、実践レベルで身につけたい！」
と思い、開発に取り掛かりました。

## 開発環境と使用技術

### 開発環境

OS：macOS<br>
IDE：フロントエンド → IntelliJ IDEA CE,　バックエンド　 → Visual Studio Code

### 使用技術

＜フロントエンド＞<br>
言語：TypeScript<br>
フレームワーク：Next.js<br>
ライブラリ：React, Redux, Axios<br>
スタイル：Tailwind CSS<br>
テスト：JEST, React Testing Library 　 ← 実装中<br>
デプロイ：Vercel<br>
パッケージ管理ツール：npm<br>

＜バックエンド＞<br>
言語：Java<br>
フレームワーク：Spring Boot<br>
データベース：MySQL（Heroku JawsDB）<br>
デプロイ：Heroku<br>
パッケージ管理ツール：Maven<br>

＜その他＞<br>
GitHub（ソースコード管理）<br>
Firebase Authentication（認証）<br>
React icons（アイコン）<br>

などなど。。。

## 機能概要

個々の機能を紹介する前にざっくり説明すると、こんな感じです。

- 一般的なタスク管理アプリだが、機能面でこだわったポイントは以下 2 点。
	- 「カテゴリ」「スケジュール」という２つの属性をタスクに紐づけられるようにした。
	- Memo 欄を大きくし、各タスク内にノートのように色々と書き込めるようにした。
- Google アカウントでログインする。
- データの流れは、こんな感じ。
  フロントエンド ←→ axios ←→ API サーバ（SpringBoot） ←→ MySQL
- フロントエンドでの状態管理は Redux Toolkit（その他 React Hooks）を使用

それでは、個々の機能を説明します！

### ログイン

ログインには Firebase Authentication を使用しました。<br>
Googole ログインのみに対応しています。<br>



![image.png](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/3817219/560054cb-8ea2-ae21-f070-90fd5e2b33b9.png)

### 新規タスク追加

新規タスクの追加はこのフォームから行います。<br>
![image.png](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/3817219/89053edc-2458-7895-be33-e181c53396a9.png)

「Detail ↓」　をクリックすると、詳細を入力するフォームが現れます。<br>
タイトルのみ必須入力となっています。<br>
![image.png](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/3817219/244a6b4b-b1e8-70db-e8f9-15de2674ec8c.png)

### 新規カテゴリ / スケジュール追加

カテゴリとスケジュールは、タスクに紐づける情報となります。<br>
仕事で Microsoft ToDo を使用しているのですが、１タスクにつき１リストしか割り当てができず、
「業務の種類」と「いつやるか」の２つの属性を持たせて管理したいとずっと思っていました。<br>

そこで今回、「カテゴリ」と「スケジュール」の２つの属性をタスクにつけられるようにしました。
![image.png](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/3817219/483e4d2c-6647-615b-3eb0-0c119508214a.png)

追加したカテゴリ/スケジュールは、新規タスク追加フォームのプルダウンにて選択可能となります。<br>

![image.png](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/3817219/8e5c353d-0aee-9d5b-a749-78e4ed1bd78f.png)

### タスクリストとタスク詳細

![image.png](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/3817219/59dedb22-25d5-081c-088a-48bccc1493fd.png)

#### タスクリスト

- フォームから追加されたタスクが表示されます。

- カテゴリタブ（緑タブ）とスケジュールタブ（紫タブ）を選択でき、
  カテゴリ × スケジュールに当てはまるタスクを表示する仕組みとなっています。
  上のスクショの場合、カテゴリが「Backend」かつ、スケジュールが「Today」のタスクが表示されています。

- 横幅が狭いため、カテゴリタブは、右上の　「< Tab >ボタン」を使ってページ化しています。

- 各タブは、名前の編集（鉛筆マーク）、削除（× マーク）が可能です。

	- 編集された場合は、対象のタスクのカテゴリ/スケジュールに即時に反映されます。
	- 削除した場合は、対象のタスクも全て削除されます。

	  ![image.png](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/3817219/0311ccb1-ad4d-e8fa-5c18-bb694c365939.png)

- タスクカード上のチェックボックスをクリックすると完了未完了が入れ替わり、動的にタスクが移動します。
- タスクのタイトルをクリックすると、画面右側のタスク詳細に、詳細が表示されます。

#### タスク詳細

- 各項目をクリックすると編集可能になります。
  また、編集した内容は、フォーカスが外れたタイミングで、即座に Redux とバックエンドに反映されます。
- 右下の「Delete ボタン」からタスクを削除することができます。
- 冒頭で述べた通り、メモ欄が大きいのが特徴です。以下のようにノート替わりに使用できます。
  ![image.png](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/3817219/679ba1c7-3685-ab01-872d-8e59003656f6.png)

## 苦労した点

- 状態管理
  - 上記で説明した機能が、各コンポーネントに分かれているわけですが、タスクのデータ、カテゴリのデータ、スケジュールのデータ等を全てのコンポーネントに共通して持たせ、どこかでデータが CRUD されたら、全てのコンポーネントに即時反映させなければいけない。
  これは、SPA では当たり前で、仕組み化されている動きですが、実装するのはとても難しかったです。
  - というより、Redux を理解するのが難しかったです。
  <br>

- デプロイ
	- フロントエンドのデプロイは、Vercel で行い、割と難なくできたのですが、バックエンドに大変苦戦しました。<br>
	最初は AWS を使って試みましたが、インフラ構築等の知識が乏しいが故にすぐに挫折し、Heroku で再挑戦しました。<br>
	application.properties 等の設定ファイルに関する知識が浅かったのも苦戦した原因かと思います。<br>
	- 結論、Verecl にしろ、Heroku にしろ、GitHub の push と同時にデプロイされるのは、とても便利だと感じました。

## 最後に

最後までお読みいただきありがとうございました。<br>

この個人開発を通して、これまでインプットした内容をもとに、自力で実施に動くアプリケーションを作ることができ、とても自信がつきました。<br>

開発をする過程で様々なエラーにぶつかり、それを乗り越えることで、エンジニアとしてのレベルが上がっていくのを、身をもって体験することができました。<br>

今後もアウトプットをたくさんし、技術レベルを上げていきたいと思います！！！<br>

ありがとうございました！
****