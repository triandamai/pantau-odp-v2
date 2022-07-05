package com.trian.data.utils.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.webkit.MimeTypeMap
import com.google.gson.Gson
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.URLEncoder
import java.util.*

class FileManager {
    companion object {
        fun saveEcgFileToInternalStorage(
            context: Context,
            fileName: String,
            data: List<Int>,
            name: String,
            gender: String,
            systole: String,
            diastole: String,
            hr: String,
            hrv: String,
        ): String {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use { fos ->
                val gson = Gson()
                val jsonData = gson.toJson(
                   ""
                )
                fos.write(jsonData.toByteArray())
                fos.flush()
                fos.close()
            }
            return fileName
        }

        fun saveFileStethoscope(
            fileName: String,
        ): FileOutputStream {
            val rootFileStethoscope = File(
                Environment.getExternalStorageDirectory(),
                "CexupDevice/Stethoscope/"
            )
            if (!rootFileStethoscope.exists()) {
                rootFileStethoscope.mkdirs()
            }
            val pcmFile = File(rootFileStethoscope, fileName)
            return FileOutputStream(pcmFile)
        }

        fun readFileStethoscope(fileName: String): File {
            val directoryFile =
                File(Environment.getExternalStorageDirectory(), "CexupDevice/Stethoscope/")
            if (!directoryFile.exists()) {
                directoryFile.mkdirs()
            }
            return File(directoryFile, fileName)
        }

        fun saveImageToInternalStorage(
            context: Context,
            bitmapImage: Bitmap,
            imageFileName: String
        ): String {
            context.openFileOutput(imageFileName, Context.MODE_APPEND).use { fos ->
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
            }
            return context.filesDir.absolutePath
        }

        fun getImageFromInternalStorage(context: Context, imageFileName: String): Bitmap? {
            val directory = context.filesDir
            val file = File(directory, imageFileName)
            return BitmapFactory.decodeStream(FileInputStream(file))
        }

        fun getEcgFileFromInternalStorage(context: Context, imageFileName: String): File? {
            return try {
                context.openFileInput(imageFileName).use { stream ->
                    stream.bufferedReader().use {
                        it.readText()
                    }
                    val directory = context.filesDir
                    val file = File(directory, imageFileName)
                    if (file.exists()) {
                        return file
                    }
                    return file
                }
            } catch (e: Exception) {
                null
            }
        }

        fun getEcgFileFromInternalStorageString(context: Context, imageFileName: String): String {
            context.openFileInput(imageFileName).use { stream ->
                val text = stream.bufferedReader().use {
                    it.readText()
                }
                return text
            }
        }

        fun deleteImageFromInternalStorage(context: Context, imageFileName: String): Boolean {
            val dir = context.filesDir
            val file = File(dir, imageFileName)
            return file.delete()
        }

        fun bitmapToFile(
            context: Context,
            bitmap: Bitmap,
            fileName: String
        ): File? {// File name like "image.png"
            //create a file to write bitmap data

            return try {
                val dir = context.filesDir
                val file = File(dir, fileName)
                file.createNewFile()

                //Convert bitmap to byte array
                val bos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
                val bitmapdata = bos.toByteArray()

                //write the bytes in file
                val fos = FileOutputStream(file)
                fos.write(bitmapdata)
                fos.flush()
                fos.close()
                file
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }

        }

        fun getExtension(file: File): String {
            val encoded: String = try {
                URLEncoder.encode(file.name, "UTF-8").replace("+", "%20")
            } catch (e: Exception) {
                file.name
            }

            return MimeTypeMap.getFileExtensionFromUrl(encoded).lowercase(Locale.getDefault())
        }
    }
}