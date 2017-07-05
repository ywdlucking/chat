/**
 * Created by admin on 2017/7/4.
 */

//保存数据
var dataChat = {};
//p2p聊天
var sock = new SockJS("/endpointChat"); //1
var stomp = Stomp.over(sock);
stomp.connect('guest', 'guest', function (frame) {
    stomp.subscribe("/user/queue/notifications", handleNotification);//2
});

function handleNotification(message) {
    var name = $(".m-list .active").attr('alt');
    var msg = JSON.parse(message.body);
    var ali = "<li>" +
        "<p class='time'> <span>" + msg.time + "</span></p>" +
        "<div>" + "<img class='avatar' width='30' height='30' src='" + msg.img + "'/>" +
        "<p class='msg-text'>" + msg.context + "</p>"
    "</div>" +
    "</li>";
    $('.message ul').append(ali);
    if(dataChat[name] === undefined){
        dataChat[name] = [];
    }
    dataChat[name].push(ali);
}
function sendToChat(text) {
    stomp.send("/chat", {}, text);
}

//聊天室
var sockRoom = new SockJS("/endpointWisely"); //1
var stompRoom = Stomp.over(sockRoom);
stompRoom.connect('guest', 'guest', function (frame) {
    stompRoom.subscribe("/topic/getResponse", handleChatRoom);//2
});

function handleChatRoom(message) {
    var msg = JSON.parse(message.body);
    var ali = "<li>" +
        "<p class='time'> <span>" + msg.time + "</span></p>" +
        "<div>" + "<img class='avatar' width='30' height='30' src='" + msg.img + "'/>" +
        "<p class='msg-text'>" + msg.context + "</p>"
    "</div>" +
    "</li>";
    $('.message ul').append(ali);
    var cr = dataChat.聊天室;
    if (cr === undefined || cr === null) {
        dataChat['聊天室'] = [ali];
    } else {
        cr.push(ali);
        dataChat['聊天室'] = cr;
    }
}
function sendToChatRoom(text) {

    stompRoom.send("/chatRoom", {}, text);
}

function sentMsg() {
    if (event.ctrlKey && event.keyCode == 13) {
        var context = $("#message-text").val();
        if (context.length < 1) {
            alert("消息不能为空");
            return;
        }
        var img = $(".m-card img").attr('src');
        var name = $(".m-list .active p").text();
        var date = new Date();
        var hours = ("0" + (date.getHours() + 1)).slice(-2);
        var minutes = ("0" + (date.getMinutes() + 1)).slice(-2);
        var time = (hours + ":" + minutes);
        var msg = {
            name: name,
            img: img,
            context: context,
            time: time
        };
        var type = $("#chatType").val();
        if (type == 'ROOM') {
            sendToChatRoom(JSON.stringify(msg));
        } else {
            sendToChat(JSON.stringify(msg));
            showSendMsg(img, time, context);
        }
        $("#message-text").val("");
    }
}

function showSendMsg(img, time, context) {
    var name = $(".m-list .active img").attr('alt');
    var ali = "<li>" +
        "<p class='time'> <span>" + time + "</span></p>" +
        "<div>" + "<img class='avatar' width='30' height='30' src='" + img + "'/>" +
        "<p class='msg-text' style='background: #62b900'>" + context + "</p>"
    "</div>" +
    "</li>";
    $('.message ul').append(ali);
    if(dataChat[name] === undefined){
        dataChat[name] = [];
    }
    dataChat[name].push(ali);
}

function choiceSession(that, type) {
    $("#message-text").attr("disabled", false);
    clearMsg()
    $(".m-list li").removeClass('active');
    that.setAttribute("class", "active");
    if (type == 'ROOM') {
        $("#chatType").val("ROOM");
    } else {
        $("#chatType").val("CHAT");
    }
    showCurrentMsg(that.innerText.trim());
}

function clearMsg() {
    var ul = $(".main .message ul");
    var ali = $(".main .message li");
    for (var i = 0; i < ali.length; i++) {
        // ul.remove(ali[i]);
        ali[i].remove();
    }
}

function showCurrentMsg(n) {
    var ali = dataChat[n];
    if(ali !== undefined){
        var ul = $(".main .message ul");
        for (var i = 0; i < ali.length; i++) {
            ul.append(ali[i]);
        }
    }
}