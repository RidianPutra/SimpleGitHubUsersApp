package com.ridianputra.core.data

import com.ridianputra.core.data.source.local.LocalDataSource
import com.ridianputra.core.data.source.remote.RemoteDataSource
import com.ridianputra.core.data.source.remote.network.ApiResponse
import com.ridianputra.core.data.source.remote.response.UsersResponse
import com.ridianputra.core.domain.model.GitHubUsers
import com.ridianputra.core.domain.repository.IGitHubUsersRepository
import com.ridianputra.core.utils.AppExecutors
import com.ridianputra.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GitHubUsersRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGitHubUsersRepository {

    override fun getAllUsers(): Flow<Resource<List<GitHubUsers>>> =
        object : NetworkBoundResource<List<GitHubUsers>, List<UsersResponse>>() {
            override fun loadFromDB(): Flow<List<GitHubUsers>> {
                return localDataSource.getAllUsers().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UsersResponse>>> =
                remoteDataSource.getAllUsers()

            override suspend fun saveCallResult(data: List<UsersResponse>) {
                val usersList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertUsers(usersList)
            }

            override fun shouldFetch(data: List<GitHubUsers>?): Boolean =
                data == null || data.isEmpty()
        }.asFlow()

    override fun getFavoriteUsers(): Flow<List<GitHubUsers>> {
        return localDataSource.getFavoriteUsers().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteUser(users: GitHubUsers, state: Boolean) {
        val usersEntity = DataMapper.mapDomainToEntity(users)
        appExecutors.diskIO().execute { localDataSource.setFavoriteUser(usersEntity, state) }
    }
}
