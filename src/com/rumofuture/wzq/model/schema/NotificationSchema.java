package com.rumofuture.wzq.model.schema;

/**
 * Created by WangZhenqi on 2017/09/29.
 */

/**
 * CREATE TABLE notification (
 *  id          INT(11)      NOT NULL AUTO_INCREMENT,
 *  inviter_id  INT(11)      NOT NULL,
 *  invitee_id  INT(11)      NOT NULL,
 *  content     VARCHAR(255) NOT NULL,
 *  is_checked  BOOLEAN               DEFAULT FALSE,
 *  type        INT(2)                DEFAULT 0,
 *  create_time DATETIME              DEFAULT NOW(),
 *  FOREIGN KEY (inviter_id)
 *  REFERENCES user(id),
 *  FOREIGN KEY (invitee_id)
 *  REFERENCES user(id),
 *  PRIMARY KEY (id)
 * )
 * ENGINE = INNODB
 * DEFAULT CHARSET = UTF8;
 */

public class NotificationSchema {
    public static final class Table {
        public static final String NAME = "notification";

        public static final class Cols {
            public static final String ID = "id";
            public static final String NOTIFIER_ID = "notifier_id";
            public static final String TARGET_ID = "target_id";
            public static final String CONTENT = "content";
            public static final String IS_CHECKED = "is_checked";
            public static final String TYPE = "type";
            public static final String CREATE_TIME = "create_time";
        }
    }
}