# ユーザーマニュアル (最終更新日 2024/1/15)
## Role_Admin(ログインIDがadmin)でwebアプリケーションを利用したときの利用方法

### 1.「 http://150.89.233.204 」にアクセスした後に最初に表示されるページでの利用方法
・webアプリケーションをブラウザで開いた際,背景に大工大の枚方キャンパスの画像が映し出され,
上側に黄緑色で「東盤雀」,その下に青色で「待合室へ移動」,その下にwebアプリケーションの大まかな内容説明として「このアプリは授業ごとにアンケートを作成・投票するためのツールです。」と表示される.

「待合室へ移動」をクリックすると,ログインページへ移動し,UsernameとPasswordの入力フォームが表示されるので,Usernameには「admin」のいずれかを入力し,Passwordには共通で「pass」と入力して「Sign in」をクリックしてください.

・「Sign in」をクリックすると、待合室のページに移動します.

### 2．待合室のページでの利用方法
・画面には「待合室」と「Hi (ログインした名前)」,「アンケートを集計する科目を追加」,「収集中のアンケート」,「画面設定」,「ログアウト」が表示される.

・「アンケートを集計する科目を追加」
	クリックすると折りたたみが解除され、もう一度クリックすると折りたたまれます.
	折りたたみが解除されていると入力欄と「追加」,「リセット」というボタンが存在する.
	入力欄には追加したい科目の名称を入力してください.
	入力後,Enterキーを入力するか追加ボタンを押すと科目を追加することができます.
	リセットボタンは入力中の文字を入力欄から削除できます.

・「収集中のアンケート」
	クリックすると折りたたみが解除され,もう一度クリックすると折りたたまれます。
	折りたたみが解除されていると「数学」,「国語」等が上下に並んでいます.
	それらのいずれかの科目をクリックすると,対応する科目のアンケートのページに移動します.
	
・「画面設定」
	クリックすると折りたたみが解除され、もう一度クリックすると折りたたまれます.
	折りたたみが解除されていると「画面サイズ設定」,「字間」,「行間」が表示されます.
	・「画面サイズ設定」下には,「A+」と「A-」というボタンが存在し,前者をクリックすると文字が大きくなり、後者をクリックすると文字が小さくなります.
	・「字間」下にはバーが存在し,バー上に存在する点を左右に移動させることで文字間隔を操作できます.
	・「行間」は「字間」同様の操作であり,行間を操作できます.

・「ログアウト」
	クリックすると「Are you sure you want to long out?」「Log Out」と表示され、「Log Out」をクリックするとこのアプリにアクセスした後に最初に表示されるページに移動します.

### 3．アンケートのページでの利用方法
・画面には「ログアウト」と,このページに移動する際にクリックした科目名,今まで追加したアンケート,アンケート追加欄,「戻る」が表示される.
・「ログアウト」
	クリックすると「Are you sure you want to long out?」「Log Out」と表示され、「Log Out」をクリックするとこのアプリにアクセスした後に最初に表示されるページに移動します.

・今まで追加したアンケート

今まで追加したアンケートは

    		・id:1 質問文:2*3=
    		・id:1 削除
    		・id:2 質問文:aaa
    		・id:2 削除
      
が続いている部分と,

		質問Id:1
		選択肢1「2」：5, 選択肢2「4」：6, 選択肢3「6」：7, 選択肢4「8」：1
  
と続く部分で構成されている.
前者の部分はアンケートのidとそのアンケート文が表示されている.
その下に書かれている。削除とはid1の隣に書かれている削除をクリックするとそのidに対する質問文や選択肢、その選択肢の回答についてもDB上から削除される.
後者の部分は各アンケートごとの選択肢の内容や今までのそれぞれの回答回数が表示されている.
  「」内に書かれていることが選択肢の文で,その左の「:数字」が回答回数である.
この回答回数の部分は非同期通信によりリアルタイムで回答の増加を確認することができる.

・アンケート追加欄
	画面下部に存在する白い部分である.
		質問文と書かれている部分の左に入力欄が存在し,アンケート文を入力できる.
		回答文と書かれている部分の下に４つの入力欄が存在しアンケートに対する選択肢を４つ入力する.
	追加をクリックするとアンケートを送信することができ,リセットをクリックすると５つの入力欄の内容が削除される.
	合計５つの入力欄には何か入力されていなければ送信することができない.

・「戻る」
	クリックすると待合室のページに移動する.