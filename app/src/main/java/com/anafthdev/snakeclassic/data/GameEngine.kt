package com.anafthdev.snakeclassic.data

import com.anafthdev.snakeclassic.data.model.Point
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.random.Random

class GameEngine(
	private val board: Board,
	private val snake: Snake
) {
	
	private var engineJob: Job? = null
	private var listener: GameListener? = null
	
	private var isPaused: Boolean = false
	
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
				x = Random.nextInt(0, board.width),
				y = Random.nextInt(0, board.height)
			)
		} while (newFoodPosition in snake.bodies)
		
		board.updateFoodPosition(newFoodPosition)
	}
	
	fun restart() {
		snake.bodies.apply {
			clear()
			add(Point(0,0))
		}
		
		randomFood()
	}
	
	fun start() {
		randomFood()
		
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
	}
	
	fun resume() {
		isPaused = false
	}
	
	fun stop() {
        engineJob?.cancel()
		engineJob = null
	}
	
	interface GameListener {
		
		fun onGameOver()
		
	}
	
}