// --------------------------------------------------------------------------------
// Retrieve all devices and perform search and pagination based on it. (Bad Method)
// --------------------------------------------------------------------------------

// $(function () {
//     var $searchId = $("#searchId");
//     var $devices = $("#devices");
//     var deviceTable = '#deviceTable';
//     var deviceTemplate = $('#deviceTemplate').html();
//     var $maxRows = $('#maxRows');
//
//     function addDevice (device){
//         $devices.append(Mustache.render(deviceTemplate, device));
//     }
//
//     function myPagination(){
//
//         $('.pagination').html('');
//         var trNum = 0;
//         var totalRows = $(deviceTable + ' tbody tr' ).length;
//         var maxRows;
//         if($maxRows.val() == null)
//             maxRows = 100;
//         else
//             maxRows = $maxRows.val();
//         $(deviceTable + ' tr:gt(0)').each(function () {
//             trNum ++;
//             if(trNum > maxRows){
//                 $(this).hide();
//             }else{
//                 $(this).show();
//             }
//         });
//         if(totalRows > maxRows){
//             var totalPage = Math.ceil(totalRows/maxRows);
//             $('.pagination').append("<li data-page='prev' class='page-item'><button class='page-link'>Previous</button></li>");
//             for (var i=1;i<=totalPage;i++){
//                 $('.pagination').append("<li data-page='" + i + "' class='page-item'><button class='page-link'> " + i + "</button></li>");
//             }
//             $('.pagination').append("<li data-page='next' class='page-item'><button class='page-link'>Next</button></li>");
//         }
//
//         var $current = $('.pagination li:first-child+');
//         $current.addClass('active');
//         $('.pagination li').on('click', function () {
//             var pageNum = $(this).attr("data-page");
//             if(pageNum == 'prev'){
//                 if($current.attr("data-page") == '1'){
//                     pageNum = 1;
//                 }else{
//                     $current = $current.prev();
//                     pageNum = $current.attr("data-page");
//                 }
//             }
//             else if(pageNum == 'next'){
//                 if($current.attr("data-page")== totalPage){
//                     pageNum = totalPage;
//                 }else{
//                     $current = $current.next();
//                     pageNum = $current.attr("data-page");
//                 }
//             }
//             else{
//                 $current = $(this);
//             }
//             var trIndex = 0;
//             $('.pagination li').removeClass('active');
//             $current.addClass('active');
//             $(deviceTable + ' tr:gt(0)').each(function () {
//                 trIndex++;
//                 if(trIndex > (maxRows*pageNum) || trIndex <= (maxRows*pageNum)-maxRows){
//                     $(this).hide();
//                 }else{
//                     $(this).show();
//                 }
//             });
//         });
//     }
//
//     $maxRows.on('change' , function (){
//         myPagination();
//     });
//
//     $.ajax({
//         type: 'GET',
//         url: '/devices',
//         success: function (data) {
//             $.each(data, function (i, device) {
//                 addDevice(device);
//             });
//             myPagination();
//         },
//         error: function () {
//             alert('Error loading devices');
//         }
//     });
//
//     $searchId.keyup( function () {
//         $devices.empty();
//         var myUrl;
//         if ($searchId.val() == ''){
//             myUrl = '/devices';
//         }else {
//             myUrl = '/devices/search/'+$searchId.val();
//         }
//         $.ajax({
//             type: 'GET',
//             url: myUrl,
//             success: function (data) {
//                 if(data == '') {
//                     $devices.append("<tr><td colspan='6' class='text-center'>Empty List of Devices</td></tr>");
//                 }else {
//                     $.each(data, function (i, device) {
//                         addDevice(device);
//                     });
//                     myPagination();
//                 }
//
//             },
//             error: function () {
//                 alert('Error loading devices');
//             }
//         });
//
//     });
// });