package com.anafthdev.snakeclassic.common

import android.util.Size
import com.anafthdev.snakeclassic.data.CollisionType
import com.anafthdev.snakeclassic.data.Direction
import com.anafthdev.snakeclassic.model.Point
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.random.Random

class GameEngine(
	private val board: Board,
	private val snake: Snake,
	private val gameConfiguration: GameConfiguration
) {
	
	private var engineJob: Job? = null
	private var listener: GameListener? = null
	
	private var isPaused: Boolean = true
	
	init {
		board.setListener(object : Board.BoardListener {
			override fun onSizeChanged(size: Size) {
				if (size.width != 0 && size.height != 0) {
					try {
						randomFood()
					} catch (e: IllegalArgumentException) {
						// Random range is empty: [0, -1]
						Timber.e(e)
					}
				}
			}
		})
	}
	
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
		listener?.onRestart()
		
		isPaused = true
		
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
				delay(gameConfiguration.movementDelay.toLong())
				
				if (!isPaused) snake.move()
				
				when (checkCollision()) {
					CollisionType.Body, CollisionType.Board -> {
						listener?.onGameOver()
						stop()
					}
					CollisionType.Food -> {
						listener?.onEat()
						snake.addBody()
						randomFood()
					}
					else -> {}
				}
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
		
		fun onRestart()
		
		fun onEat()
		
	}
	
}