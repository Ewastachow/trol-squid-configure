$(document).on('click', '.saveconfig', function (e) {
    e.preventDefault();
    $.ajax({
        type:'GET',
        url:'/save'
    }).done(function (result) {
        location.reload();
    });
});

function savingConfiguration() {
    $("#save-config").attr('disabled','disabled');
    $("#save-config").html(
      "<i class='fa fa-floppy-o' aria-hidden='true'></i> Saving configuration"
    );
}

function configurationSaved() {
    $("#save-config").removeAttr('disabled');
    $("#save-config").html(
        "<i class='fa fa-floppy-o' aria-hidden='true'></i> Save configuration"
    );
}

function checkState() {
    $.ajax({
        type:'GET',
        url:'/save/state',
        dataType: 'json'
    }).done(function (result) {
        if(result == 1){
            configurationSaved();
        }else {
            savingConfiguration();
            setTimeout(checkState,1000);
        }
    });
}

$( document ).ready(function (e) {
    checkState();
});