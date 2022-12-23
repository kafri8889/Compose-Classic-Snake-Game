package com.anafthdev.snakeclassic.data

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.StateObject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anafthdev.snakeclassic.data.model.Point
import timber.log.Timber

class Snake {
	
	var direction by mutableStateOf(Direction.Right)
	private set
	
	var bodies = mutableStateListOf(
//		Point(7, 0),
//		Point(6, 0),
//		Point(5, 0),
//		Point(4, 0),
//		Point(3, 0),
//		Point(2, 0),
//		Point(1, 0),
		Point(0, 0)
	)
	
	fun updateDirection(newValue: Direction) {
		direction = newValue
	}
	
	fun addBody() {
		val lastBody = bodies.last()
		val secondBodyFromLast = bodies.getOrNull(bodies.lastIndex - 1)
		
		val newBodyPoint = when {
			secondBodyFromLast == null -> {
				when (direction) {
					Direction.Up -> Point(lastBody.x, lastBody.y + 1)
					Direction.Down -> Point(lastBody.x, lastBody.y - 1)
					Direction.Left -> Point(lastBody.x + 1, lastBody.y)
					Direction.Right -> Point(lastBody.x - 1, lastBody.y)
				}
			}
			lastBody.x < secondBodyFromLast.x -> {
				// Snake direction -> Right
				
				Point(
					x = lastBody.x - 1,
					y = lastBody.y
				)
			}
			lastBody.x > secondBodyFromLast.x -> {
				// Snake direction -> Left
				
				Point(
					x = lastBody.x + 1,
					y = lastBody.y
				)
			}
			lastBody.y < secondBodyFromLast.y -> {
				// Snake direction -> Down
				
				Point(
					x = lastBody.x,
					y = lastBody.y - 1
				)
			}
			lastBody.y > secondBodyFromLast.y -> {
				// Snake direction -> Up
				
				Point(
					x = lastBody.x,
					y = lastBody.y + 1
				)
			}
			else -> throw IllegalStateException("Invalid direction")
		}
		
		bodies.add(newBodyPoint)
	}
	
	fun move() {
		val headPoint = when (direction) {
			Direction.Up -> {
				Point(bodies[0].x, bodies[0].y - 1)
			}
			Direction.Down -> {
				Point(bodies[0].x, bodies[0].y + 1)
			}
			Direction.Left -> {
				Point(bodies[0].x - 1, bodies[0].y)
			}
			Direction.Right -> {
				Point(bodies[0].x + 1, bodies[0].y)
			}
		}
		
		bodies.apply {
			for (i in (size - 1) downTo 1) {
				set(i, get(i -1))
			}
			
			set(0, headPoint)
		}
		
//		when (direction) {
//			Direction.Up -> {
//
//			}
//			Direction.Down -> {
//				bodies.apply {
//					for (i in indices) {
//						val newPoint = Point(get(i).x, get(i).y + 1)
//						Timber.i("newPoint: $newPoint")
//
//						set(i, newPoint)
//					}
//				}
//			}
//			Direction.Left -> {
//
//			}
//			Direction.Right -> {
//				bodies.apply {
//					for (i in indices) {
//						val newPoint = Point(get(i).x + 1, get(i).y)
//						Timber.i("newPoint: $newPoint")
//
//						set(i, newPoint)
//					}
//				}
//			}
//		}
	}
	
}