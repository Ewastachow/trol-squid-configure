$(document).on('click', '.removedomain', function (e) {
    e.preventDefault();
    var listId = $('#listId').val();
    var tr = $(this).parent().parent();
    var domainId = tr.find(".id").val();
    var url = "/domains/list/"+listId+"/edit/"+domainId;
    var domain = $(this).val();
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

$(document).on('click', '.addDomain', function (e) {
    e.preventDefault();
    var input = $(this).parent().find(".domain");
    var listId = $('#listId').val();
    var url = "/domains/list/"+listId+"/edit"
    var domain = input.val();
    $.ajax({
        type: 'POST',
        url: url,
        contentType: "application/json",
        data: domain
    }).done(function(result){
        location.reload();
        //TODO
    });
});

$(document).on('click', '.editdomain', function (e) {
    var tr = $(this).parent().parent();
    var domainDiv = tr.find(".domain");
    var domain = domainDiv.text();
    domainDiv.html(
        "<input type='text' class='newDomain' value='"+domain+"'/>"
    );
    var remove = tr.find(".removedomain");
    var edit = tr.find(".editdomain");

    edit.parent().html(
        "<button class='btn btn-success applyEdit'>Apply</button>")
    ;
    remove.parent().html(
        "<button class='btn btn-warning cancelEdit'>Cancel</button>"
    );
});

$(document).on('click', '.applyEdit', function (e) {
    var tr = $(this).parent().parent();
    var newDomain = tr.find(".newDomain").val();
    var domainId = tr.find(".id").val();
    var listId = $('#listId').val();
    var url = "/domains/list/"+listId+"/edit/"+domainId;
    $.ajax({
        type:'PUT',
        url: url,
        contentType: "application/json",
        data: newDomain
    }).done(function(result){
        location.reload();
        //TODO show result
    });
});

$(document).on('click', '.cancelEdit', function (e) {
    var tr = $(this).parent().parent();
    var oldDomain = tr.find(".oldDomain").val();
    var domainDiv = tr.find(".domain");
    domainDiv.html(
        oldDomain
    );
    var cancel = tr.find(".cancelEdit");
    var apply = tr.find(".applyEdit");

    cancel.parent().html(
        "<button class='btn btn-danger removedomain'>Remove</button>"
    );
    apply.parent().html(
        "<button class='btn btn-default editdomain'>Edit</button>"
    );
});

