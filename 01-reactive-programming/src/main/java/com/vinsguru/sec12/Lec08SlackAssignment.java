package com.vinsguru.sec12;

import com.vinsguru.common.Util;
import com.vinsguru.sec12.assignment.SlackMember;
import com.vinsguru.sec12.assignment.SlackRoom;
import reactor.core.publisher.Sinks;

public class Lec08SlackAssignment {

    public static void main(String[] args) {
        var room = new SlackRoom("reactor");
        var sam = new SlackMember("sam");
        var jake = new SlackMember("jake");
        var mike = new SlackMember("mike");

        room.addMember(sam);
        room.addMember(jake);

        sam.says("Hi all ...");

        Util.sleepSeconds(4);
        jake.says("Hey!");
        sam.says("I simply want to say hi..");

        Util.sleepSeconds(4);
        room.addMember(mike);
        mike.says("Hey guys.. glad to be there.. ");
    }

}
