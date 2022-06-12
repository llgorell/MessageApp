package com.example.messageappebcom.data.repository

import com.example.messageappebcom.data.local.MessageDataBase
import com.example.messageappebcom.data.mapper.convertToMessages
import com.example.messageappebcom.data.mapper.toMessage
import com.example.messageappebcom.data.mapper.toMessageEntity
import com.example.messageappebcom.data.remote.MessageApi
import com.example.messageappebcom.domain.model.Messages
import com.example.messageappebcom.domain.repository.MessageRepository
import com.example.messageappebcom.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageRepositoryImpl @Inject constructor(
    private val api: MessageApi,
    private val db: MessageDataBase
) : MessageRepository {
    private val dao = db.dao
    override suspend fun getMessages(): Flow<Resource<List<Messages>>> {

        return flow {
            //start loading
            emit(Resource.Loading(true))
            try {
                val response = api.getMessages().messages

                    dao.clearMessages()
                    dao.insertMessage(
                        response.map { it.toMessageEntity() }
                    )
                    val dataDB = dao.getListMessage()
                dataDB?.let{
                    emit(Resource.Success(
                        data = dataDB.map { it.convertToMessages() }
                    ))
                }

                    emit(Resource.Loading(false))

            }catch(e: Exception) {

            }




            //get data from db and emit it
            /*val listMessage = dao.getMessages()
            emit(Resource.Success(
                data = listMessage.map { it.convertToMessages() }
            ))

            //check DB and finish flow
            if (listMessage.isNotEmpty()){
                emit(Resource.Loading(false))
                return@flow
            }*/

            //in else condition
          /*  val remoteListMessage =

                try {
                    val response = api.getMessages().messages
                    response.map { it.toMessage() }
                }
                catch (e: IOException) {
                    e.printStackTrace()
                    emit(Resource.Error("couldn`t load data"))

                }
            catch (e: HttpException){
                e.printStackTrace()
                emit(Resource.Error("couldn`t load data"))

            }

            remoteListMessage?.let { listMessage->
                dao.clearMessages()

                    dao.insertMessage(
                        listMessage.map { it.toMessageEntity() }
                    )
                emit(Resource.Success(
                    data = dao.getListMessage().map { it.convertToMessages()}
                ))
                emit(Resource.Loading(false))
            }*/
        }
    }
}