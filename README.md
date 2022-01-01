# World-Simulation-Game

The main goal of the project is the implementation of a 2D virtual world simulator. The virtual world have the structure of a two-dimensional NxM grid. In this world, simple life
forms exist, each with different behavior depending on its species. Every organism occupy exactly one cell of the world's 2D grid array. Each cell contain no more
than a single organism at a time (in case of a collision, one of the organisms is removed from this cell). <br />

During each turn, every living organism in the world perform an action appropriate to their kind. Animals move, while others remain static (plants). There is also a human who is controlled by the player, his movement depends on what arrow key is pressed. New organisms can be created by clicking empty cell and choosing one specie from the list.
