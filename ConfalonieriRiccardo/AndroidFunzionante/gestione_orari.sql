-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Creato il: Apr 30, 2017 alle 09:22
-- Versione del server: 10.1.19-MariaDB
-- Versione PHP: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestione_orari`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `orario`
--

CREATE TABLE `orario` (
  `Id` int(11) NOT NULL,
  `IdProf` int(11) NOT NULL,
  `Ora` int(3) NOT NULL,
  `giorno` int(3) NOT NULL,
  `classe` varchar(10) NOT NULL,
  `aula` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `orario`
--

INSERT INTO `orario` (`Id`, `IdProf`, `Ora`, `giorno`, `classe`, `aula`) VALUES
(358, 1, 1, 1, '2BI', 'LFS'),
(359, 1, 2, 1, '3AL', '3AL'),
(360, 1, 3, 1, '5BL', '5BL'),
(361, 1, 4, 1, '', ''),
(362, 1, 5, 1, '.', '.'),
(363, 1, 6, 1, '.', '.'),
(364, 1, 1, 2, '2BI', '2BI'),
(365, 1, 2, 2, '2AL', '2AL'),
(366, 1, 3, 2, '5AL', '5AL'),
(367, 1, 4, 2, '3AL', 'LFS'),
(368, 1, 5, 2, '.', '.'),
(369, 1, 6, 2, '.', '.'),
(370, 1, 1, 3, '-', '-'),
(371, 1, 2, 3, '-', '-'),
(372, 1, 3, 3, '-', '-'),
(373, 1, 4, 3, '-', '-'),
(374, 1, 5, 3, '-', '-'),
(375, 1, 6, 3, '-', '-'),
(376, 1, 1, 4, '5BL', '5BL'),
(377, 1, 2, 4, '.', '.'),
(378, 1, 3, 4, '1AL', '1AL'),
(379, 1, 4, 4, '4AL', '4AL'),
(380, 1, 5, 4, '5AL', '5AL'),
(381, 1, 6, 4, '', ''),
(382, 1, 1, 5, '.', '.'),
(383, 1, 2, 5, '.', '.'),
(384, 1, 3, 5, '1AL', 'LFS'),
(385, 1, 4, 5, '.', '.'),
(386, 1, 5, 5, '4AL', 'LFS'),
(387, 1, 6, 5, '2BI', '2BI'),
(388, 1, 1, 6, '5BL', '5BL'),
(389, 1, 2, 6, '5AL', '5AL'),
(390, 1, 3, 6, '3AL', '3AL'),
(391, 1, 4, 6, '2AL', 'LFS'),
(392, 1, 5, 6, '4AL', '4AL');

-- --------------------------------------------------------

--
-- Struttura della tabella `professore`
--

CREATE TABLE `professore` (
  `Id` int(11) NOT NULL,
  `NomeProf` varchar(60) NOT NULL,
  `materiaInsegnata` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `professore`
--

INSERT INTO `professore` (`Id`, `NomeProf`, `materiaInsegnata`) VALUES
(1, 'AGOSTONI G.', 'materia');

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `id` int(11) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `professore` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`id`, `username`, `password`, `professore`) VALUES
(2, 'riccardo', 'password', 1),
(3, 'test', 'test', 0);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `orario`
--
ALTER TABLE `orario`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `IdProf` (`IdProf`);

--
-- Indici per le tabelle `professore`
--
ALTER TABLE `professore`
  ADD PRIMARY KEY (`Id`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `orario`
--
ALTER TABLE `orario`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=393;
--
-- AUTO_INCREMENT per la tabella `professore`
--
ALTER TABLE `professore`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `orario`
--
ALTER TABLE `orario`
  ADD CONSTRAINT `orario_ibfk_1` FOREIGN KEY (`IdProf`) REFERENCES `professore` (`Id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
