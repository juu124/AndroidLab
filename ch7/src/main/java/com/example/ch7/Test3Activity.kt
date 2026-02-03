package com.example.ch7

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch7.databinding.ActivityTest3Binding

class Test3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.dateButton.setOnClickListener {
            // 매개변수를 안쓴다고 하면 해당 부분에는 어떤 값이 들어가게 될까
            val dateDialog = DatePickerDialog(
                this, { _, year, month, dayOfMonth -> // 이벤트 콜백 .. 유저가 선택한 날짜
                    Toast.makeText(this, "${year}년 ${month+1}월 ${dayOfMonth}일", Toast.LENGTH_SHORT)
                        .show()
                },
                // 초기 선택되어야 하는 날짜
                2026, 2-1, 3
            )
            dateDialog.show()
        }
        binding.timeButton.setOnClickListener {
            val timeDialog = TimePickerDialog(
                this, { _, hourOfDay, minute ->
                    Toast.makeText(this, "${hourOfDay}시 ${minute}분", Toast.LENGTH_SHORT).show()
                }, 13, 30, false // 24시간? 12시간?
            )
            timeDialog.show()
        }
        binding.vibratorButton.setOnClickListener {
            // 진동을 위한 시스템 서비스, 버전별로 상이하다.
            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val manager = getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager
                manager.defaultVibrator
            } else {
                // deprecated api는 사용하지 않는 것을 권장한다.
                getSystemService(VIBRATOR_SERVICE) as Vibrator
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // DEFAULT_AMPLITUDE : 시스템에서 적적하게 선택된 진동 감도
                vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                vibrator.vibrate(1000)
            }
        }

        binding.beepButton.setOnClickListener {
            // 시스템 기본 효과음. 식별자 획득
            val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            // 식별자를 대입해서. 음을 울릴 수 있는 Rington 획득
            val ringtone = RingtoneManager.getRingtone(this, uri)
            ringtone.play()
        }
    }
}