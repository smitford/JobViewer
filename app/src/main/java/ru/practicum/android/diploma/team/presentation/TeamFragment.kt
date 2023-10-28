package ru.practicum.android.diploma.team.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import ru.practicum.android.diploma.databinding.FragmentTeamBinding

class TeamFragment : Fragment() {

    private var _binding : FragmentTeamBinding? = null
    private val binding get() = _binding

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamBinding.inflate(inflater,container,false)
        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}