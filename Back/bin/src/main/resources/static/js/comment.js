$(function () {
    $('#btn-comment-save').click(function () {
        var data ={
            post_id : $('#post_id').val(),
            author : $('#author').val(),
            content : $('#content').val(),
        }
        $.ajax({
            type: 'POST',
            url : '/comment/',
            contentType :'application/json ; charset=utf-8',
            data : JSON.stringify(data),
        }).done(function () {
            alert("댓글이 등록되었습니다");
            window.location.href = "/post"
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    })
});