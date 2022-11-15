<template>
    <div class="w-full overflow-x-hidden min-h-screen bg-gray-800 flex items-center justify-center">
        <div class="w-2/3 h-auto bg-white p-4 my-4">
            <h1 class="text-2xl font-bold mb-4">Students performance analysis</h1>
            <form @submit.prevent="submit">
                <div class="flex flex-col items-start gap-2 mb-4">
                    <label class="text-lg font-bold">Upload dataset</label>
                    <input type="hidden" v-model="form.file" />
                    <input type="file" @input="handleFile" />
                </div>
                <div class="flex flex-col items-start gap-2 mb-4">
                    <span class="text-lg font-bold">Sorting algorithm</span>
                    <label v-for="method of methods" :key="method" class="flex text-lg gap-2">
                        <input type="radio" name="sorting" v-model="form.sorting" :value="method" />
                        <span class="capitalize">{{ method }}</span>
                    </label>
                </div>
                <button type="submit" class="text-lg border px-3 py-2 border-gray-400 mb-4 mx-auto block">Analyze</button>
            </form>
            <template v-if="dataset.grades.length > 0">
                <hr class="border-black my-4" />
                <p class="text-center">Results count: {{ dataset.grades.length }}</p>
                <p class="text-center mb-4">Time of execution in nanoseconds: {{ dataset.timeElapsed }}</p>
                <button type="button" class="text-lg border px-3 py-2 border-gray-400 mb-4 mx-auto block" @click="generate">Download contents</button>
                <table class="mx-auto" id="results-table">
                    <thead>
                    <tr class="border border-black">
                        <th class="border border-b-black text-center px-8 py-1">#</th>
                        <th class="border border-b-black text-center px-8 py-1">Student</th>
                        <th class="border border-b-black text-center px-8 py-1">Grade</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="(item, index) of dataset.grades" :key="item">
                        <td class="border text-center font-bold">{{ index + 1 }}</td>
                        <td class="border text-center">{{ item.name }}</td>
                        <td class="border text-center">{{ item.grade }}</td>
                    </tr>
                    </tbody>
                </table>
            </template>
        </div>
    </div>
</template>

<script setup lang="ts">
import { onBeforeMount, ref } from 'vue';
import axios, { AxiosError } from 'axios';
import { useToast } from 'vue-toastification';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';
import { format } from 'date-fns';

type Dataset = {
    message: string,
    grades: Array<{ name: string, grade: number }>,
    timeElapsed: number
}

const toast = useToast();
const methods = ref([]);
const dataset = ref<Dataset>({
    message: '',
    grades: [],
    timeElapsed: -1
});
const form = ref({
    file: '',
    sorting: ''
});

const generate = () => {
    const PDF = new jsPDF();
    let table = [];
    let itemNo = 1;
    for (const gradeInfo of dataset.value.grades) {
        table.push({
            '#': `${itemNo}`,
            'Student': gradeInfo.name,
            'Grade': gradeInfo.grade
        });
        itemNo++;
    }
    autoTable(PDF, { html: '#results-table' });
    const today = new Date();
    PDF.save(`${format(today, 'yyyyMMdd_HHmmss')}.pdf`);
}

const handleFile = (event: any) => {
    form.value.file = event.target.files[0];
}

const submit = () => {
    const formData = new FormData();
    for (const [key, value] of Object.entries(form.value)) {
        formData.append(key, value);
    }
    axios.post('http://localhost:8000/api/sort', formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }).then((response) => {
        dataset.value = response.data;
    }).catch((err: AxiosError) => {
        toast.error(err.message);
    });
}

onBeforeMount(() => {
    axios.get('http://localhost:8000/api/methods').then((response) => {
        const { data } = response;
        methods.value = data.methods;
    });
});
</script>
