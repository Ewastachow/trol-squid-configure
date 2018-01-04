function getErrorInfo(message) {
    return "<div class='alert alert-danger'>"+ message +"</div>";
}

$(document).on('click', '.removeword', function (e) {
    e.preventDefault();
    var listId = $('#listId').val();
    var tr = $(this).parent().parent();
    var wordId = tr.find(".id").val();
    var url = "/words/list/"+listId+"/edit/"+wordId;
    var word = $(this).val();
    $.ajax({
        type: 'DELETE',
        url: url,
        contentType: "application/json",
    }).done(function(result){
        location.reload();
        //TODO
    });
    $(this).parent().parent().remove();
});

$(document).on('click', '.addWord', function (e) {
    e.preventDefault();
    var input = $(this).parent().find(".word");
    var listId = $('#listId').val();
    var url = "/words/list/"+listId+"/edit"
    var word = input.val();
    $.ajax({
        type: 'POST',
        url: url,
        contentType: "application/json",
        data: word
    }).done(function(result){
        console.log(result);
        console.log(result.message);
        location.reload();
        //TODO
    }).fail(function (xhr, status, error) {
        console.log(xhr);
        var response = JSON.parse(xhr.responseText);
        var error = response.error[0];
        $("#adderror").html(getErrorInfo(error));
    });
});

$(document).on('click', '.editword', function (e) {
    var tr = $(this).parent().parent();
    var wordDiv = tr.find(".word");
    var word = wordDiv.text();
    wordDiv.html(
        "<input type='text' class='newWord' value='"+word+"'/>"
    );
    var remove = tr.find(".removeword");
    var edit = tr.find(".editword");

    edit.parent().html(
        "<button class='btn btn-success applyEdit'>Apply</button>")
    ;
    remove.parent().html(
        "<button class='btn btn-warning cancelEdit'>Cancel</button>"
    );
});

$(document).on('click', '.applyEdit', function (e) {
    var tr = $(this).parent().parent();
    var newWord = tr.find(".newWord").val();
    var wordId = tr.find(".id").val();
    var listId = $('#listId').val();
    var url = "/words/list/"+listId+"/edit/"+wordId;
    $.ajax({
        type:'PUT',
        url: url,
        contentType: "application/json",
        data: newWord
    }).done(function(result){
        console.log(result);
        location.reload();
    }).fail(function (xhr, status, error) {
        var response = JSON.parse(xhr.responseText);
        var error = response.error[0];
        //$("#adderror").html(getErrorInfo(error));
    });
});

$(document).on('click', '.cancelEdit', function (e) {
    var tr = $(this).parent().parent();
    var oldWord = tr.find(".oldWord").val();
    var wordDiv = tr.find(".word");
    wordDiv.html(
        oldWord
    );
    var cancel = tr.find(".cancelEdit");
    var apply = tr.find(".applyEdit");

    cancel.parent().html(
        "<button class='btn btn-danger removeword'>Remove</button>"
    );
    apply.parent().html(
        "<button class='btn btn-default editword'>Edit</button>"
    );
});

