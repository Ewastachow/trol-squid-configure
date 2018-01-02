$(document).on('click', '.removeWord', function (e) {
    e.preventDefault();
    var listName = $('#listName').val();
    var word = $(this).val();
    var data = {
        "listName" : listName,
        "word" : word
    };
    $.ajax({
        type: 'DELETE',
        url: '/words/list/edit',
        contentType: "application/json",
        data: JSON.stringify(data)
    }).done(function(result){
        location.reload();
        //TODO
    });
    $(this).parent().parent().remove();
});

$(document).on('click', '.addWord', function (e) {
    e.preventDefault();
    var input = $(this).parent().find(".word");
    var listName = $('#listName').val();
    var word = input.val();
    var data = {
        "listName" : listName,
        "word" : word
    };
    $.ajax({
        type: 'POST',
        url: '/words/list/edit',
        contentType: "application/json",
        data: JSON.stringify(data)
    }).done(function(result){
        location.reload();
        //TODO
    });
});

$(document).on('click', '.editWord', function (e) {
    var tr = $(this).parent().parent();
    var td = $(this).parent().parent().find(".word");
    var word = td.text();
    var newValue = "<input type='text' class='newValue' value='"+word+"'/>";
    var oldValue = "<input type='hidden' class='oldValue' value='"+word+"'/>";
    var applyEdit = "<td><button class='applyEdit'>Apply</button></td>";
    var cancelEdit = "<td><button class='cancelEdit'>Cancel</button></td>";
    tr.html(
        '<td class="word">'+newValue+oldValue+'</td>'+applyEdit+cancelEdit
    );
});

$(document).on('click', '.applyEdit', function (e) {
    var tr = $(this).parent().parent();
    var td = tr.find(".word");
    var newValue = td.find(".newValue").val();
    var oldValue = td.find(".oldValue").val();
    var listName = $('#listName').val();
    var data = {
        "listName" : listName,
        "oldValue" : oldValue,
        "newValue" : newValue
    };
    $.ajax({
        type:'PUT',
        url:'/words/list/edit',
        contentType: "application/json",
        data:JSON.stringify(data)
    }).done(function(result){
        location.reload();
        //TODO show result
    });
});

$(document).on('click', '.cancelEdit', function (e) {
    var tr = $(this).parent().parent();
    var td = $(this).parent().parent().find(".oldValue");
    var word = td.val();
    var newValue = '<td class="word">'+word+'</td>';
    var removeDomain = '<td><button class="removeWord" value='+word+'>Remove</button></td>';
    var editWord = '<td><button class="editWord" value='+word+'>Edit</button></td>';
    tr.html(
        newValue+removeDomain+editWord
    );
});

