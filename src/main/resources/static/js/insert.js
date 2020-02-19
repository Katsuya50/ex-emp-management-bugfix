$(function(){
	$("#confirmPassword").on("keyup",function(){
		$.ajax({
			url:'http://localhost:8080/check-password',
			type:'POST',
			dataType:'json',
			data:{  //リクエストパラメータ情報
				password:$('#password').val(),
				confirmPassword:$('#confirmPassword').val(),
			},
			async:true //非同期で処理を行う
		}).done(function(data){
			//検索成功時にはページに結果を反映
			console.log(data);
			//コンソールに取得したJSONデータを表示
			console.dir(JSON.stringify(data));
			$("#errorMessage").html(data.errorMessage);
		}).fail(function(XMLHttpRequest, textStatus, errorThrown){
			//検索失敗時には、その旨をダイアログ表示しエラー情報をコンソールに記載
			alert('エラーが発生しました。');
			console.log("XMLHttpRequest:" + XMLHttpRequest.status);
			console.log("textStatus:" + textStatus);
			console.log("errorThrown:" + errorThrown.message);
		});
	});
});