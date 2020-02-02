// -----------------------------------------------------------------------------------
// Retrieve devices based on Pageable and perform search and pagination. (Good Method)
// -----------------------------------------------------------------------------------

$(function () {
    var $searchId = $("#searchId");
    var $devices = $("#devices");
    var deviceTemplate = $('#deviceTemplate').html();
    var $maxRows = $('#maxRows');
    var $mac = $('#filterByMac');
    var $chipSet = $('#filterByChipSet');
    var $build = $('#filterByBuild');
    var $manufacturer = $('#filterByManufacturer');

    function addDevice (device){
        $devices.append(Mustache.render(deviceTemplate, device));
    }

    function myPagination(rowNum, totalPages, myUrl){
        $('.pagination').html('');
        var trNum = 0;
        var totalPage = totalPages;
        var maxRows = rowNum;
        var url = myUrl;

        if(totalPage>1){
            $('.pagination').append("<li data-page='prev' class='page-item'><button class='page-link'><i class='fa fa-backward'></i> Previous</button></li>");
            for (var i=1;i<=totalPage;i++){
                $('.pagination').append("<li data-page='" + i + "' class='page-item'><button class='page-link'> " + i + "</button></li>");
            }
            $('.pagination').append("<li data-page='next' class='page-item'><button class='page-link'>Next <i class='fa fa-forward'></i></button></li>");
        }

        var $current = $('.pagination li:first-child+');
        $current.addClass('active');
        $('.pagination li').on('click', function () {
            $devices.empty();
            var pageNum = $(this).attr("data-page");
            if(pageNum == 'prev'){
                if($current.attr("data-page") == '1'){
                    pageNum = 1;
                }else{
                    $current = $current.prev();
                    pageNum = $current.attr("data-page");
                }
            }
            else if(pageNum == 'next'){
                if($current.attr("data-page")== totalPage){
                    pageNum = totalPage;
                }else{
                    $current = $current.next();
                    pageNum = $current.attr("data-page");
                }
            }
            else{
                $current = $(this);
            }
            $('.pagination li').removeClass('active');
            $current.addClass('active');

            if(url.includes('?')){
                if (url.includes('page')){
                    url = url.substring(0, url.indexOf('page='));
                    url = url + 'page=' + pageNum;
                }else
                    url = url + '&page=' + pageNum;
            }else{
                url = url + '?page=' + pageNum;
            }

            $.ajax({
                type: 'GET',
                url: url,
                success: function (data) {
                    $.each(data.content, function (i, device) {
                        addDevice(device);
                    });
                },
                error: function () {
                    alert('Error loading devices');
                }
            });
        });
    }

    var maxRows;
    if($maxRows.val() == null)
        maxRows = 20;
    else
        maxRows = $maxRows.val();

    function getDevices(myUrl){
        $devices.empty();

        $.ajax({
            type: 'GET',
            url: myUrl,
            success: function (data) {
                if(data.content == '')
                    $devices.append("<tr><td colspan='6' class='alert alert-info text-center'>Empty List of Devices</td></tr>");
                else {
                    $.each(data.content, function (i, device) {
                        addDevice(device);
                    });
                    myPagination(maxRows, data.totalPages, myUrl);
                }
            },
            error: function () {
                alert('Error loading devices');
            }
        });
    }

    getDevices('/devices');

    $maxRows.on('change', function () {
        maxRows = $(this).val();
        var myUrl;
        if($mac.val() == '' && $chipSet.val() == '' && $build.val() == '' && $manufacturer.val() == '')
            myUrl = '/devices?search=' + $searchId.val() + '&size=' + maxRows;
        else
            myUrl = '/getDevices?mac='+ $mac.val() + '&chipSet=' + $chipSet.val() + '&build=' + $build.val() + '&manufacturer=' + $manufacturer.val() + '&size=' + maxRows;
        getDevices(myUrl);
    });

    $searchId.keyup( function () {
        $devices.empty();
        $mac.val('');
        $build.val('');
        $chipSet.val('');
        $manufacturer.val('');
        var myUrl = '/devices?search=' + $searchId.val() + '&size=' + maxRows;
        getDevices(myUrl);
    });

    $('#filter').on('click', function () {
        $devices.empty();
        $searchId.val('');
        var myUrl = '/getDevices?mac='+ $mac.val() + '&chipSet=' + $chipSet.val() + '&build=' + $build.val() + '&manufacturer=' + $manufacturer.val() + '&size=' + maxRows;
        getDevices(myUrl);
    });

});