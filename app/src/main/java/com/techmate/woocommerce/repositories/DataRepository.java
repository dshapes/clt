package com.techmate.woocommerce.repositories;

import com.techmate.woocommerce.api.ApiClient;
import com.techmate.woocommerce.api.ApiService;

public class DataRepository {

    private ApiService apiService;
    private static DataRepository projectRepository;

    private DataRepository() {
        apiService = ApiClient.getPOSTClient(false);
    }

    public synchronized static DataRepository getInstance() {
        //TODO No need to implement this singleton in Part #2 since Dagger will handle it ...
        if (projectRepository == null) {
            if (projectRepository == null) {
                projectRepository = new DataRepository();
            }
        }
        return projectRepository;
    }

    /*public LiveData<List<Project>> getProjectList(String userId) {
        final MutableLiveData<List<Project>> data = new MutableLiveData<>();

        apiService.getProjectList(userId).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, HomeResponse<List<Project>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                // TODO better error handling in part #2 ...
                data.setValue(null);
            }
        });

        return data;
    }*/

    /*public LiveData<HomeResponse> registerUser(Map<String, String> params) {
        final MutableLiveData<HomeResponse> data = new MutableLiveData<>();

        apiService.registerUser(params).enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                simulateDelay();
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }*/

    private void simulateDelay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}