package com.example.p4_daa_alexandre;

import androidx.lifecycle.ViewModel;

public class HomeFragmentViewModel extends ViewModel {

    //constructor
    private lateinit var viewModel: MyViewModel
    private lateinit var androidViewModel: MyAndroidViewModel
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        androidViewModel =
                ViewModelProvider(this).get(MyAndroidViewModel::class.java)
    }


}
