
set b 0
set c 0
set j 1

  :loop
com 1 a
add b a
igt b 500
  set pc stateChange
set pc loop

  :stateChange
sub b 500
set x FFFFh
set y FFFFh

mul x c
xor c j
mul y c

com 10h x
com 11h y
com 20h c

set pc loop
