$(function(){
	$.ajax({
		url:'http://localhost:8080/search-employees',
		type:'POST',
		dataType:'json',
		async:true //非同期で処理を行う
	}).done(function(data){
		console.log(data);
		$("#search-name").autocomplete({
			source: data
		});
	});

//	$("#search-name").on("keyup",function(){
//		console.log($(function(){
//			$.ajax({
//				url:'http://localhost:8080/search-employees',
//				type:'POST',
//				dataType:'json',
//				async:true //非同期で処理を行う
//			}).done(function(data){
//				alert("a");
//				console.log("[" + data.employees + "]");
//				return "[" + data.employees + "]";
//			}).fail(function(XMLHttpRequest, textStatus, errorThrown){
//				return "[]";
//			});
//		}));
//	});
});