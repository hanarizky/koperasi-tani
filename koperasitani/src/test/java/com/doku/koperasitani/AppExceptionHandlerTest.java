package com.doku.koperasitani;

import com.doku.koperasitani.controller.MemberController;
import com.doku.koperasitani.controller.ProductController;
import com.doku.koperasitani.controller.TransactionController;
import com.doku.koperasitani.service.MemberService;
import com.doku.koperasitani.service.ProductService;
import com.doku.koperasitani.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({MemberController.class, ProductController.class, TransactionController.class, TransactionService.class})
@Slf4j
public class AppExceptionHandlerTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private ProductService productService;

    @MockBean
    private TransactionService transactionService;

    @Test
    public void createMember() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/anggota")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\": \"3\",\n" +
                        "    \"nama\": \"B\",\n" +
                        "    \"alamat\": \"B\",\n" +
                        "    \"jumlahSimpanan\": \"100000\"\n" +
                        "}"))
                .andExpect(status().is4xxClientError())
                .andDo(print()).andReturn();
        String content = result.getResponse().getContentAsString();
        log.info(content);
    }

    @Test
    public void getMember() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/anggota/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(print()).andReturn();

        String content = result.getResponse().getContentAsString();
        log.info(content);

    }

    @Test
    public void createProduct() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/produk")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"idProduk\":\"4\",\n" +
                        "\t\"hargaProduk\": \"15000\",\n" +
                        "\t\"namaProduk\": \"d\",\n" +
                        "\t\"qtyProduk\": \"30\"\n" +
                        "\n" +
                        "}"))
                .andExpect(status().is4xxClientError())
                .andDo(print()).andReturn();
        String content = result.getResponse().getContentAsString();
        log.info(content);
    }

    @Test
    public void getProduct() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/produk/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(print()).andReturn();

        String content = result.getResponse().getContentAsString();
        log.info(content);

    }

//    @Test
//    public void createTransaction() throws Exception {
//        MvcResult result = this.mockMvc.perform(post("/transaksi")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\n" +
//                        "\t\"id\":\"1\",\n" +
//                        "\t\"idProduk\":\"10\",\n" +
//                        "\t\"qty\": \"5\"\n" +
//                        "\n" +
//                        "}"))
//                .andExpect(status().is4xxClientError())
//                .andDo(print()).andReturn();
//        String content = result.getResponse().getContentAsString();
//        log.info(content);
//    }

}
