package awesome.shizzle.urbandictionary.model

import awesome.shizzle.urbandictionary.model.Response
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@UnstableDefault
class SerializationUnitTest {

    val jsonString =
        """{"list":[{"definition":"[The 4th] element required to [summon] [Captain Planet]","permalink":"http://water.urbanup.com/1024563","thumbs_up":3375,"sound_urls":["https://api.twilio.com/2008-08-01/Accounts/ACd09691b82112e4b26fce156d7c01d0ed/Recordings/RE4515e7c7a293e6eea847315e0ef22b3e"],"author":"Captain Planet Summoner","word":"Water","defid":1024563,"current_vote":"","written_on":"2005-01-27T00:00:00.000Z","example":"EARTH\r\nFIRE\r\n[WIND]\r\nWATER\r\nHEART\r\n\r\nGO PLANET!\r\n\r\nBy your [powers] combined, I am [Captain Planet]!","thumbs_down":927},{"definition":"why [the hell] [are you] looking up water for on [the urban dictionary].","permalink":"http://water.urbanup.com/6362066","thumbs_up":429,"sound_urls":["https://api.twilio.com/2008-08-01/Accounts/ACd09691b82112e4b26fce156d7c01d0ed/Recordings/RE4515e7c7a293e6eea847315e0ef22b3e"],"author":"all your base belong to us","word":"water","defid":6362066,"current_vote":"","written_on":"2012-01-12T00:00:00.000Z","example":"[seriously], why [the hell] did you [look up] water","thumbs_down":118},{"definition":"a smoking [substance] which is made from a mix of [PCP] and [embalming fluid].","permalink":"http://water.urbanup.com/3407230","thumbs_up":274,"sound_urls":["https://api.twilio.com/2008-08-01/Accounts/ACd09691b82112e4b26fce156d7c01d0ed/Recordings/RE4515e7c7a293e6eea847315e0ef22b3e"],"author":"VolcomStoneLC","word":"water","defid":3407230,"current_vote":"","written_on":"2008-10-14T00:00:00.000Z","example":"\"[Yo nigga] you [got dat] water?\"\r\n\r\n\"[fo sho nigga]..\"","thumbs_down":80},{"definition":"[Another word] for [dust]([pcp])","permalink":"http://water.urbanup.com/108433","thumbs_up":1038,"sound_urls":["https://api.twilio.com/2008-08-01/Accounts/ACd09691b82112e4b26fce156d7c01d0ed/Recordings/RE4515e7c7a293e6eea847315e0ef22b3e"],"author":"uncle fester","word":"water","defid":108433,"current_vote":"","written_on":"2003-04-26T00:00:00.000Z","example":"[Lets go] [cop] some water","thumbs_down":540},{"definition":"What you get when you combine two [Hydrogen] [atoms] and a single Oxygen [atom]. Try it, it's fun to make on those rainy days. Or if it is raining you could go outside and just hold a cup out and catch some of this mystical substance known as water.","permalink":"http://water.urbanup.com/1081143","thumbs_up":1040,"sound_urls":["https://api.twilio.com/2008-08-01/Accounts/ACd09691b82112e4b26fce156d7c01d0ed/Recordings/RE4515e7c7a293e6eea847315e0ef22b3e"],"author":"Dan the Man","word":"water","defid":1081143,"current_vote":"","written_on":"2005-02-23T00:00:00.000Z","example":"[Water] is [what the] oceans are [made] of.","thumbs_down":554},{"definition":"the [fundamental] principal of life for all ages of human beings and animals. It is [transparent] and takes the shape of many forms. It has a definite volume and an [indefinite] shape and can fit into any container.","permalink":"http://water.urbanup.com/13396837","thumbs_up":27,"sound_urls":["https://api.twilio.com/2008-08-01/Accounts/ACd09691b82112e4b26fce156d7c01d0ed/Recordings/RE4515e7c7a293e6eea847315e0ef22b3e"],"author":"Greg Papadoupalas","word":"Water","defid":13396837,"current_vote":"","written_on":"2018-11-26T00:00:00.000Z","example":"Bob gets water at the [dollar store] for [99 cents] a [bottle].","thumbs_down":10},{"definition":"An abundant liquid that is very addicting. Everyone, including Chuck Norris, need this type of particular chemical in them, daily. Water is consist of two [hydrogen] [atoms] and one oxygen [atom]. It's very addicting compare to other drinks. Alcohol comes in second against the legendary water. No one can survive without this common \"chemical.\" Water is an essential part of life. Our body is made up of 98% of this addicting chemical. Water can be either sell legally or illegally. ","permalink":"http://water.urbanup.com/2868738","thumbs_up":448,"sound_urls":["https://api.twilio.com/2008-08-01/Accounts/ACd09691b82112e4b26fce156d7c01d0ed/Recordings/RE4515e7c7a293e6eea847315e0ef22b3e"],"author":"BenchMax345","word":"Water","defid":2868738,"current_vote":"","written_on":"2008-02-20T00:00:00.000Z","example":"**[Diffy] EQ**\r\nDavid: Dr. Tang, may I [get a drink of water].\r\nDr. Tang: No.\r\nDavid: I need my common daily chemical or I'll die.\r\nDr. Tang: Sure.\r\n**David at Chick-fil-A** \r\nDavid: I want the ultimate meal.\r\nManager: Alrighty, what would you like to drink?\r\nDavid: Water...the [essence of life]...\r\nManager: But you get free coke with \"the ultimate meal.\"\r\nDavid: God dammit sir! I just want my water!","thumbs_down":273},{"definition":"Another way of [saying] [bye],one or [peace].","permalink":"http://water.urbanup.com/3054027","thumbs_up":115,"sound_urls":["https://api.twilio.com/2008-08-01/Accounts/ACd09691b82112e4b26fce156d7c01d0ed/Recordings/RE4515e7c7a293e6eea847315e0ef22b3e"],"author":"STYLEs+DiiMPSz=4eva","word":"water","defid":3054027,"current_vote":"","written_on":"2008-05-01T00:00:00.000Z","example":"A: Yo i'll [c u] [lata] tonite.\r\nB: yea no doubt\r\nA:[iight then] one\r\nB:iight water ","thumbs_down":63},{"definition":"A [erection's] [arch nemesis]","permalink":"http://water.urbanup.com/12476166","thumbs_up":3,"sound_urls":["https://api.twilio.com/2008-08-01/Accounts/ACd09691b82112e4b26fce156d7c01d0ed/Recordings/RE4515e7c7a293e6eea847315e0ef22b3e"],"author":"Suki1234","word":"Water","defid":12476166,"current_vote":"","written_on":"2018-01-24T00:00:00.000Z","example":"Andrew and Jamie were naked and making out near the pool. [Jamie's] pussy was soaking wet and Andrew cock was rock-solid and 11 inches long. When Andrew stood up he accidentally fell in the pool. Then it happened. His testicles instantly shriveled up, his cock stung, and the water flushed out all the blood and filled his dick. When he got out, Jamie was laughing at the size of his dick. Instead of being as solid as rock, his dick was as soft as a blanket and instead of being 11 inches long, his cock was the size of a [popcorn kernel]. Jamie ran up to him and they kept making out. When they kissed, Andrew had something go into his mouth, While kissing, Andrew opened his eyes and looked at Jamie's sweater. There was a pack of VIAGRA [in the pocket]... and one was missing. Suddenly all the water in his cock started dripping out and his dick became warm again, his testicles slowly started [drooping], and his cock instantly started stretching out again, eventually poking Jamie. As they laughed, they kept making out.","thumbs_down":0},{"definition":"Where [New Orleans] [used] to be.","permalink":"http://water.urbanup.com/4004579","thumbs_up":300,"sound_urls":["https://api.twilio.com/2008-08-01/Accounts/ACd09691b82112e4b26fce156d7c01d0ed/Recordings/RE4515e7c7a293e6eea847315e0ef22b3e"],"author":"Roastmasters","word":"Water","defid":4004579,"current_vote":"","written_on":"2009-05-29T00:00:00.000Z","example":"Water falls from the [friking] [sky] you friking [dumby], get a life go outside.","thumbs_down":188}]}"""

    @Test
    fun serializationWorks() {
        val deserialized = Json.parse(Response.serializer(), jsonString)
        assertThat(deserialized.list.size).isEqualTo(10)
        assertThat(deserialized.list[0].writtenByOnAsString()).isEqualTo("Captain Planet Summoner\non January 27, 2005")
    }

    @Test
    fun deserializationWorks() {
        val toSerialize = Response(
            listOf(
                Response.UrbanDefinition(
                    "a smoking [substance] which is made from a mix of [PCP] and [embalming fluid].",
                    "http://water.urbanup.com/3407230",
                    274,
                    listOf("https://api.twilio.com/2008-08-01/Accounts/ACd09691b82112e4b26fce156d7c01d0ed/Recordings/RE4515e7c7a293e6eea847315e0ef22b3e"),
                    "VolcomStoneLC",
                    "water",
                    3407230,
                    "",
                    "2008-10-14T00:00:00.000Z",
                    "\"[Yo nigga] you [got dat] water?\"\r\n\r\n\"[fo sho nigga]..\"",
                    80
                )
            )
        )


        val serializedString = Json.stringify(Response.serializer(), toSerialize)
        Truth.assertThat(serializedString)
            .isEqualTo("{\"list\":[{\"definition\":\"a smoking [substance] which is made from a mix of [PCP] and [embalming fluid].\",\"permalink\":\"http://water.urbanup.com/3407230\",\"thumbs_up\":274,\"sound_urls\":[\"https://api.twilio.com/2008-08-01/Accounts/ACd09691b82112e4b26fce156d7c01d0ed/Recordings/RE4515e7c7a293e6eea847315e0ef22b3e\"],\"author\":\"VolcomStoneLC\",\"word\":\"water\",\"defid\":3407230,\"current_vote\":\"\",\"written_on\":\"2008-10-14T00:00:00.000Z\",\"example\":\"\\\"[Yo nigga] you [got dat] water?\\\"\\r\\n\\r\\n\\\"[fo sho nigga]..\\\"\",\"thumbs_down\":80}]}")
    }

    @Test
    fun movieSearchDeserialization() {
        val jsonString = """{
                              "page": 1,
                              "total_results": 2,
                              "total_pages": 1,
                              "results": [
                                {
                                  "vote_count": 12803,
                                  "id": 106646,
                                  "video": false,
                                  "vote_average": 7.9,
                                  "title": "The Wolf of Wall Street",
                                  "popularity": 18.443,
                                  "poster_path": "\/vK1o5rZGqxyovfIhZyMELhk03wO.jpg",
                                  "original_language": "en",
                                  "original_title": "The Wolf of Wall Street",
                                  "genre_ids": [
                                    80,
                                    18,
                                    35
                                  ],
                                  "backdrop_path": "\/rP36Rx5RQh0rmH2ynEIaG8DxbV2.jpg",
                                  "adult": false,
                                  "overview": "A New York stockbroker refuses to cooperate in a large securities fraud case involving corruption on Wall Street, corporate banking world and mob infiltration. Based on Jordan Belfort's autobiography.",
                                  "release_date": "2013-12-25"
                                },
                                {
                                  "vote_count": 0,
                                  "id": 423766,
                                  "video": false,
                                  "vote_average": 0,
                                  "title": "The Wolf of Wall Street",
                                  "popularity": 0.6,
                                  "poster_path": "\/aNGsE1nVtQHu4pvPzNvqVaczztb.jpg",
                                  "original_language": "en",
                                  "original_title": "The Wolf of Wall Street",
                                  "genre_ids": [],
                                  "backdrop_path": null,
                                  "adult": false,
                                  "overview": "A ruthless stockbroker sells short in the copper business and ruins the life of his friends by ruining their finances.",
                                  "release_date": "1929-02-09"
                                }
                              ]
                            }"""

        val parsed = Json.parse(MoviesResponse.serializer(), jsonString)
        assertThat(parsed.results.size).isEqualTo(2)
    }
}
