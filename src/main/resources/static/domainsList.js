$(document).on('click', '.removedomain', function (e) {
    e.preventDefault();
    var listName = $('#listName').val();
    var domain = $(this).val();
    var data = {
        "listName" : listName,
        "domain" : domain
    };
    $.ajax({
        type: 'DELETE',
        url: '/lists/list/edit',
        contentType: "application/json",
        data: JSON.stringify(data)
    }).done(function(result){
        location.reload();
        //TODO
    });
    $(this).parent().parent().remove();
});

$(document).on('click', '.addDomain', function (e) {
    e.preventDefault();
    var input = $(this).parent().find(".domain");
    var listName = $('#listName').val();
    var domain = input.val();
    var data = {
      "listName" : listName,
      "domain" : domain
    };
    $.ajax({
        type: 'POST',
        url: '/lists/list/edit',
        contentType: "application/json",
        data: JSON.stringify(data)
    }).done(function(result){
        location.reload();
        //TODO
    });
});

$(document).on('click', '.editdomain', function (e) {
    var tr = $(this).parent().parent();
    var td = $(this).parent().parent().find(".domain");
    var domain = td.text();
    var newDomain = "<input type='text' class='newDomain' value='"+domain+"'/>";
    var oldDomain = "<input type='hidden' class='oldDomain' value='"+domain+"'/>";
    var applyEdit = "<td><button class='applyEdit'>Apply</button></td>";
    var cancelEdit = "<td><button class='cancelEdit'>Cancel</button></td>";
    tr.html(
        '<td class="domain">'+newDomain+oldDomain+'</td>'+applyEdit+cancelEdit
    );
});

$(document).on('click', '.applyEdit', function (e) {
    var tr = $(this).parent().parent();
    var td = tr.find(".domain");
    var newDomain = td.find(".newDomain").val();
    var oldDomain = td.find(".oldDomain").val();
    var listName = $('#listName').val();
    var data = {
        "listName" : listName,
        "oldDomain" : oldDomain,
        "newDomain" : newDomain
    };
    $.ajax({
        type:'PUT',
        url:'/lists/list/edit',
        contentType: "application/json",
        data:JSON.stringify(data)
    }).done(function(result){
        location.reload();
        //TODO show result
    });
});

$(document).on('click', '.cancelEdit', function (e) {
    var tr = $(this).parent().parent();
    var td = $(this).parent().parent().find(".oldDomain");
    var domain = td.val();
    var newDomain = '<td class="domain">'+domain+'</td>';
    var removeDomain = '<td><button class="removedomain" value='+domain+'>Remove</button></td>';
    var editDomain = '<td><button class="editdomain" value='+domain+'>Edit</button></td>';
    tr.html(
        newDomain+removeDomain+editDomain
    );
});

