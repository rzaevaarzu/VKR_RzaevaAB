package com.example.vkr_rzaevaab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vkr_rzaevaab.api.viewmodels.DeviceReservViewModel
import com.example.vkr_rzaevaab.api.viewmodels.DeviceViewModel
import com.example.vkr_rzaevaab.databinding.FragmentAddDeviceReservBinding
import com.example.vkr_rzaevaab.entities.Device
import com.example.vkr_rzaevaab.entities.DeviceReservation
import com.example.vkr_rzaevaab.sharedpreferences.SharedPref
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList
import java.util.Calendar


class AddDeviceReservFragment : Fragment() {

    val bundle = Bundle()
    private var _binding: FragmentAddDeviceReservBinding? = null
    private val binding: FragmentAddDeviceReservBinding get() = _binding!!
    private val deviceReservViewModel: DeviceReservViewModel by viewModels()
    private val deviceViewModel: DeviceViewModel by viewModels()
    private lateinit var timeAdapter: TimeAdapter
    private var selectedTime: String? = null // Переменная для хранения выбора


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddDeviceReservBinding.inflate(inflater, container, false)

        val user = SharedPref(requireContext()).getPreferenceString("id")!!.toInt()

        val item = arguments?.getParcelable<Device>("device")
        val device_id = item!!.id.toInt()

//        val item = arguments?.getString("device")!!.toInt()

        binding.deviceName.text = item!!.name.toString()
        binding.deviceCpu.text = item!!.cpu.toString()
        binding.deviceRam.text = item!!.ram.toString()
        binding.deviceMonitor.text = item!!.monitor.toString()
        binding.deviceVideocard.text = item!!.videocard.toString()

        val calendarView = binding.calendarView
        var x1 = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        var x2 = Calendar.getInstance().get(Calendar.MONTH) + 1
        var x3 = Calendar.getInstance().get(Calendar.YEAR)

        calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            // Вызывается при изменении выбранной даты
            x1 = day
            x2 = month+1
            x3 = year
        }

        val date = x1.toString() + "." + x2.toString() + "." +x3.toString()

        deviceReservViewModel.getDeviceReservByDeviceIdAndDate(device_id, date)
        val block_times = arrayListOf<String>()

        deviceReservViewModel.deviceReservList.observe(viewLifecycleOwner) { deviceReservs ->
            if (deviceReservs!=null){
                for (i in deviceReservs!!){
                    block_times.add(i.time)
                }
            }
        }

        val time = arrayListOf<String>("10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00")

//        val free_times = ArrayList<String>()
//
//        for (i in time){
//            if (i !in block_times) {
//                free_times.add(i)
//            }
//        }
//
//        if (free_times.size > 0){
//
//            //TODO
//
//
//        }
//        else if (free_times.size == 0){
//            CoroutineScope(Dispatchers.Main).launch {
//                Toast.makeText(requireContext(), "Сожалеем, но записей на этот день нет", Toast.LENGTH_SHORT).show()
//            }
//        }
//

        timeAdapter = TimeAdapter(time, block_times)
        binding.timeList.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.timeList.addItemDecoration(SpacingItemDecoration(4))
        binding.timeList.adapter=timeAdapter

        timeAdapter.onItemClick = { sel_time ->
            selectedTime = sel_time // Сохраняем в переменную фрагмента
        }



        binding.addDeviceReserv.setOnClickListener {
            if (date.isNullOrEmpty() or selectedTime.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Выберите дату и время", Toast.LENGTH_SHORT).show()
            } else {
                deviceReservViewModel.createDeviceReserv(
                    DeviceReservation(
                        userId = user,
                        deviceId = device_id,
                        date = date,
                        time = selectedTime.toString()
                    )
                )
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(
                        requireContext(),
                        "Бронирование успешно добавлено",
                        Toast.LENGTH_SHORT
                    ).show()
                    Navigation.findNavController(it)
                        .navigate(R.id.action_addDeviceReservFragment_to_deviceListFragment)
                }
            }
        }


        return binding.root
    }
}