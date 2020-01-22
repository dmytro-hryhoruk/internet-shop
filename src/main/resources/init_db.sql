CREATE SCHEMA `test` DEFAULT CHARACTER SET utf8;

CREATE TABLE `internetshop`.`items`
(
    `item_id` INT           NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(225)  NOT NULL,
    `price`   DECIMAL(6, 2) NOT NULL,
    PRIMARY KEY (`item_id`)
);

INSERT INTO `internetshop`.`items` (`item_id`, `name`, `price`)
VALUES ('', 'IPhone11', '1000');