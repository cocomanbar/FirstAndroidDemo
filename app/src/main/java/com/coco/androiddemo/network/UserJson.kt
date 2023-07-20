package com.coco.androiddemo.network

/*

{
  "status": 200,
  "message": "成功",
  "data": {
    "data": {
      "id": 3117,
      "userId": 1600932269,
      "name": "lovelychubby",
      "avatar": "https://pipijoke.oss-cn-hangzhou.aliyuncs.com//ajsdfksjakfjasklfjkasfas_54757db023a4c2f%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20200214204431.jpg",
      "description": "更多android进阶课程请在慕课搜索lovelychubby",
      "likeCount": 985,
      "topCommentCount": 200,
      "followCount": 100,
      "followerCount": 10,
      "qqOpenId": "A8747C32A5D614281E65DA5B473D1F31",
      "expires_time": 1640266383000,
      "score": 1000,
      "historyCount": 10,
      "commentCount": 3,
      "favoriteCount": 0,
      "feedCount": 10,
      "hasFollow": false
    }
  }
}

*/

// 通过插件快速生成生成

data class UserJson(
    val data: UserData?,
    val message: String?,
    val status: Int?
)

data class UserData(
    val data: UserInfo?
)

data class UserInfo(
    val avatar: String?,
    val commentCount: Int?,
    val description: String?,
    val expires_time: Long?,
    val favoriteCount: Int?,
    val feedCount: Int?,
    val followCount: Int?,
    val followerCount: Int?,
    val hasFollow: Boolean?,
    val historyCount: Int?,
    val id: Int?,
    val likeCount: Int?,
    val name: String?,
    val qqOpenId: String?,
    val score: Int?,
    val topCommentCount: Int?,
    val userId: Int?
)


/*

{
    "status": 200,
    "message": "成功",
    "data": {
        "data": [
            {
                "id": 3117,
                "userId": 1600932269,
                "name": "lovelychubby",
                "avatar": "https://pipijoke.oss-cn-hangzhou.aliyuncs.com//ajsdfksjakfjasklfjkasfas_54757db023a4c2f%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20200214204431.jpg",
                "description": "更多android进阶课程请在慕课搜索lovelychubby",
                "likeCount": 985,
                "topCommentCount": 200,
                "followCount": 100,
                "followerCount": 10,
                "qqOpenId": "A8747C32A5D614281E65DA5B473D1F31",
                "expires_time": 1640266383000,
                "score": 1000,
                "historyCount": 10,
                "commentCount": 3,
                "favoriteCount": 0,
                "feedCount": 10,
                "hasFollow": false
            },
            {
                "id": 3117,
                "userId": 1600932269,
                "name": "lovelychubby",
                "avatar": "https://pipijoke.oss-cn-hangzhou.aliyuncs.com//ajsdfksjakfjasklfjkasfas_54757db023a4c2f%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20200214204431.jpg",
                "description": "更多android进阶课程请在慕课搜索lovelychubby",
                "likeCount": 985,
                "topCommentCount": 200,
                "followCount": 100,
                "followerCount": 10,
                "qqOpenId": "A8747C32A5D614281E65DA5B473D1F31",
                "expires_time": 1640266383000,
                "score": 1000,
                "historyCount": 10,
                "commentCount": 3,
                "favoriteCount": 0,
                "feedCount": 10,
                "hasFollow": false
            }
        ]
    }
}

*/

data class StudentJson(
    val data: StudentData?,
    val message: String?,
    val status: Int?
)

data class StudentData(
    val data: List<StudentInfo>?
)

data class StudentInfo(
    val avatar: String?,
    val commentCount: Int?,
    val description: String?,
    val expires_time: Long?,
    val favoriteCount: Int?,
    val feedCount: Int?,
    val followCount: Int?,
    val followerCount: Int?,
    val hasFollow: Boolean?,
    val historyCount: Int?,
    val id: Int?,
    val likeCount: Int?,
    val name: String?,
    val qqOpenId: String?,
    val score: Int?,
    val topCommentCount: Int?,
    val userId: Int?
)