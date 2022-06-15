$(function () {
    $('#btn-save').click(function () {
        var data ={
            title : $('#title').val(),
            content : $('#content').val(),
        }

        $.ajax({
            type: 'POST',
            url : '/post',
            contentType :'application/json ; charset=utf-8',
            data : JSON.stringify(data),
        }).done(function () {
            alert("글이 등록되었습니다");
            window.location.href = "/post"
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    })
    
    $('#btn-rollback').click(function(){
    	location.href="/post";
    })
    
    $('#btn-update-page').click(function(){
    	var id = $('#id').val();
    	location.href="/post/update/"+id;
    })
    
    
    $('#btn-update').click(function () {
    	var id = $('#id').val();
    	var convertUrl = "/post/"+id;
        var data ={
            title : $('#title').val(),
            content : $('#content').val(),
            id : id
        }

        $.ajax({
            type: 'PUT',
            url : convertUrl,
            contentType :'application/json ; charset=utf-8',
            data : JSON.stringify(data),
        }).done(function () {
            alert("글이 수정되었습니다");
            window.location.href = "/post"
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    })    
    
    
    $('#btn-delete').click(function () {
     	var id = $('#id').val();
    	var convertUrl = "/post/"+id;
        $.ajax({
            type: 'DELETE',
            url : convertUrl,
            contentType :'application/json ; charset=utf-8',
        }).done(function () {
            alert("글이 삭제 되었습니다");
            window.location.href = "/post"
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    })    
    
})