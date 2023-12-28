package com.example.appproyecto.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import java.io.Serializable

@Entity(tableName = "tblPet")
data class Menu(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "nombre")
    val nombre:String,
    @ColumnInfo(name = "cantidad")
    val cantidad:String
):Serializable