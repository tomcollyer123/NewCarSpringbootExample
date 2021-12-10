DROP TABLE IF EXISTS `car` CASCADE;

CREATE TABLE `car`(
		`id` INTEGER PRIMARY KEY AUTO_INCREMENT,
		`colour`VARCHAR(100) NOT NULL,
		`make`VARCHAR(100) NOT NULL,
		`model` VARCHAR(100) NOT NULL,
		`num_of_doors` INTEGER NOT NULL,
		`price` INTEGER NOT NULL
);
		