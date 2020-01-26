$(function () {
    var $searchId = $("#searchId");
    var $devices = $("#devices");

    var deviceTemplate = $('#deviceTemplate').html();

    function addDevice (device){
        $devices.append(Mustache.render(deviceTemplate, device));
    }

    $.ajax({
        type: 'GET',
        url: '/devices',
        success: function (data) {
            $.each(data, function (i, device) {
                addDevice(device);
            })
        },
        error: function () {
            alert('Error loading devices');
        }
    })

    $searchId.keyup( function () {
        $devices.empty();
        $.ajax({
            type: 'GET',
            url: '/devices/'+$searchId.val(),
            success: function (data) {
                $.each(data, function (i, device) {
                    addDevice(device);
                });
            },
            error: function () {
                alert('Error loading devices');
            }
        });

    });
});