package com.piotrapps.iappsinterview.ui.list.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.piotrapps.iappsinterview.R
import com.piotrapps.iappsinterview.databinding.ListFragmentBinding
import com.piotrapps.iappsinterview.ui.list.recycler.PhotoUiState
import com.piotrapps.iappsinterview.ui.list.recycler.PhotosAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotosListFragment : Fragment() {

    private val viewModel: PhotosViewModel by viewModels()

    private var _binding: ListFragmentBinding? = null

    private val binding: ListFragmentBinding
        get() = _binding!!

    private val photosAdapter: PhotosAdapter by lazy {
        PhotosAdapter(
            clickListener = {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ListFragmentBinding.inflate(inflater, container, false)
            .also { _binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.photosRecycler.apply {
            layoutManager = GridLayoutManager(
                requireContext(),
                if (resources.getBoolean(R.bool.isTablet)) 3 else 1
            )
            adapter = photosAdapter
        }

        subscribeUi()
    }

    private fun subscribeUi() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.photosUiState.collectLatest { state ->
                loader.isVisible =
                    state is PhotoUiState.Loading || (state as? PhotoUiState.Success)?.photos.isNullOrEmpty()
                errorGroup.isVisible = state is PhotoUiState.Error
                (state as? PhotoUiState.Success)?.photos?.let {
                    photosRecycler.isVisible = it.isNotEmpty()
                    photosAdapter.submitList(it)
                }
            }
        }
    }
}