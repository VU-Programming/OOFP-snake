rm -r codegradedist
mkdir -p codegradedist
cd codegradedist
wget https://gitlab.com/vu-oofp/gamebase/-/archive/snake/gamebase-snake.tar.gz
tar -xvf gamebase-snake.tar.gz --strip-components=1
rm gamebase-snake.tar.gz
rm -r lib
rm -r src/main/snake/game/
rm -r src/main/engine/GameBase.scala src/main/engine/graphics/
tar cvzf snake.tar.gz *
