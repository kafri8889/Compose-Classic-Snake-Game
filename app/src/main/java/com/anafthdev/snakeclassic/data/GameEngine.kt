package com.anafthdev.snakeclassic.data

import com.anafthdev.snakeclassic.data.model.Point
import kotlinx.coroutines.*
import kotlin.random.Random

class GameEngine(
	private val board: Board,
	private val snake: Snake
) {
	
	private var engineJob: Job? = null
	private var listener: GameListener? = null
	
	private var isPaused: Boolean = true
	
	/**
	 * @return true if collision, false otherwise
	 */
	private fun checkCollision(): CollisionType {
		val head = snake.bodies[0]
		
		val collisionWithBoard = when {
			head.x > (board.width - 1) || head.x < 0 -> true
			head.y > (board.height - 1) || head.y < 0 -> true
			else -> false
		}
		
		val collisionWithBody = run {
			val bodiesWithoutHead = ArrayList(snake.bodies).apply {
				removeAt(0)  // Remove head
			}
			
			head in bodiesWithoutHead
		}
		
		val collisionWithFood = head == board.foodPosition
		
		return when {
			collisionWithFood -> CollisionType.Food
			collisionWithBody -> CollisionType.Body
			collisionWithBoard -> CollisionType.Board
            else -> CollisionType.None
		}
	}
	
	fun randomFood() {
		var newFoodPosition: Point
		
		do {
			newFoodPosition = Point(
				x = Random.nextInt(0, board.width - 1),
				y = Random.nextInt(0, board.height - 1)
			)
		} while (newFoodPosition in snake.bodies)
		
		board.updateFoodPosition(newFoodPosition)
	}
	
	fun restart() {
		snake.updateDirection(Direction.Right)
		snake.bodies.apply {
			clear()
			add(Point(0,0))
		}
		
		randomFood()
	}
	
	fun start() {
		restart()
		
		engineJob = CoroutineScope(Dispatchers.IO).launch {
			while (true) {
				delay(600)
				
				when (checkCollision()) {
					CollisionType.Body, CollisionType.Board -> {
						listener?.onGameOver()
						stop()
					}
					CollisionType.Food -> {
						snake.addBody()
						randomFood()
					}
					else -> {}
				}
				
				if (!isPaused) snake.move()
            }
		}
		
		engineJob!!.start()
	}
	
	fun pause() {
		isPaused = true
		listener?.isPlaying(false)
	}
	
	fun resume() {
		isPaused = false
		listener?.isPlaying(true)
	}
	
	fun stop() {
        engineJob?.cancel()
		engineJob = null
	}
	
	fun setListener(mListener: GameListener) {
		listener = mListener
	}
	
	interface GameListener {
		
		fun isPlaying(playing: Boolean)
		
		fun onGameOver()
		
	}
	
}