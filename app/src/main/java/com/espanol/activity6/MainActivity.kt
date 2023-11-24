package com.espanol.activity6

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.espanol.activity6.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


private const val DATE_FORMAT = "yyyy-MM-dd"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.alertDialogButton.setOnClickListener {
            showAlertDialog()
        }

        binding.datePickerButton.setOnClickListener {
            showDatePicker()
        }

        binding.timePickerButton.setOnClickListener {
            showTimePicker()
        }

        binding.itemPickerButton.setOnClickListener {
            showPickerDialog()
        }
    }

    private fun showAlertDialog() {
        var selectedChoice = ""
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Receive Updates?")
            .setMessage("Do you consent of receiving updates?")
            .setPositiveButton("Yes") { _, _ ->
                selectedChoice = "Receive updates? Yes"
                binding.alertDialogTextView.text = selectedChoice
                Toast.makeText(this, selectedChoice, Toast.LENGTH_SHORT).show()
            }.setNegativeButton("No") { _, _ ->
                selectedChoice = "Receive updates? No"
                binding.alertDialogTextView.text = selectedChoice
                Toast.makeText(this, "Receive updates? NO", Toast.LENGTH_SHORT).show()
            }
        alertDialog.show()
    }

    private fun showDatePicker() {
        var selectedDate = ""
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                selectedDate = formatSelectedDate(selectedYear, selectedMonth, selectedDayOfMonth)
                binding.datePickerTextView.text = selectedDate
                Toast.makeText(this, "Selected Date: $selectedDate", Toast.LENGTH_SHORT).show()
            },
            year,
            month,
            dayOfMonth
        )
        datePickerDialog.setTitle("Select Date")
        datePickerDialog.show()
    }

    private fun showTimePicker() {
        var selectedTime = ""
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHourOfDay, selectedMinute ->
                selectedTime = "$selectedHourOfDay:$selectedMinute"
                binding.timePickerTextView.text = selectedTime
                Toast.makeText(this, "Time selected: $selectedTime", Toast.LENGTH_SHORT).show()
            },
            hourOfDay,
            minute,
            true
        )
        timePickerDialog.setTitle("Select Time")
        timePickerDialog.show()
    }

    private fun showPickerDialog() {
        var selectedItem = ""
        val items = arrayOf("Games", "Books", "Hiking", "Singing")
        val pickerDialog = AlertDialog.Builder(this)
            .setTitle("Pick a hobby")
            .setItems(items) { _, which ->
                selectedItem = items[which]
                binding.itemPickerTextView.text = selectedItem
                Toast.makeText(this, "Your hobby is: ${items[which]}", Toast.LENGTH_SHORT).show()
            }
            .create()
        pickerDialog.show()
    }

    private fun formatSelectedDate(year: Int, month: Int, dayOfMonth: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
}