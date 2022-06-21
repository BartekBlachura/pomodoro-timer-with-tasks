# pomodoro-timer-with-tasks
### [PL] minutnik pomodoro z obsługą list zadań, najważniejsze cechy projektu:

- możliwość dodawania nowych zadań poprzez podanie nazwy zadania, określenie szacowanego czasu potrzebnego do realizacji (zadanie krótkie (mniej niż 5 min) i zadania długie) i określenie priorytetu zadania (1 (najniższy) - 3 (najwyższy));
- automatyczne sortowanie po dodaniu nowego zadania wg priorytetu z uwzględnieniem kolejności dodania (od zadań najstarszych o najwyższym priorytecie, przez zadania nowsze o najwyższym priorytecie, aż po zadania najnowsze o najniższym priorytecie;
- automatyczne wskazanie zadań, nad którymi należy pracować w danym cyklu pomodoro (naprzemiennie 4 zadania krótkie i 1 zadnie długie), jeśli zadania z poprzedniego cyklu nie zostały ukończone zostaną wskazane w kolejnym cyklu z możliwością dobrania dodatkowych zadań w przypadku zadań krótkich;
- możliwość wyświetlenia list zadań krótkich, długich i ukończonych;
- możliwość oznaczenia konkretnego zadania jako ukończonego bez uruchamiania minutnika;
- zapis do i odczyt z pliku binarnego tasks.dat list zadań krótkich, długich i ukończonych;
- możliwość konfiguracji minutnika pomodoro (czas pracy, czas krótkiej i długiej przerwy, nazwa użytkownika) wraz zapisem do i odczytem z pliku binarnego settings.dat;
- zapis logów do pliku pomodoro.log przy użyciu biblioteki log4j w wersji 2.17.2 (https://logging.apache.org/log4j/2.x/).

### możliwe kierunki dalszego rozwoju:
- graficzny interfejs użytkownika;
- współpraca z bazą danych.
