function getListeAmis() {
    $.ajax({
        type: "POST",
        url: "ajax",
        data: {
            'categorie': "getListeAmis"

        },
        success: function(html) {
            $("#displayListeAmis").html(html).show();
            $("#table").bootstrapTable();
        }
    });
}

$(document).ready(function() {
    //$("#displayListeAmis").html('<tr><td colspan="4"><strong>Chargement...</strong></td></tr>');

    //getListeAmis();



    $("#refreshListeAmis").click(function (){
        $("#displayListeAmis").html('<tr><td colspan="4"><strong>Chargement...</strong></td></tr>');
        getListeAmis();
    });

});